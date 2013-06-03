package compartirmesadetren
import grails.plugins.springsecurity.Secured
import java.text.DateFormat
import java.text.SimpleDateFormat

class SeleccionInicialController {

	static defaultAction = "trayectos"
	def trenesService
	def peticionesService
	def grailsApplication
	def mailService
	def actionService
	def twitter4jService
	def notificacionesService
	
	def trayectos() {
		setLastURI()
		def fecha = new Date().clearTime() + 2
		[trayectos: Trayecto.list(), fecha: fecha.format("dd/MM/yyyy")]
	}
	
	def trenes(Integer opcion) {
		def Date fecha
		def Trayecto trayecto
		List<PeticionesTren> peticionesTren = []
		def ofertas = false
		if (params.trayecto) {
			trayecto = Trayecto.read(params.trayecto)
			if (!opcion) {
				def dateFormat = new SimpleDateFormat("dd/MM/yyyy")
				fecha = dateFormat.parse(params.fecha).clearTime()
				if (fecha >= new Date().clearTime() + 2)
					peticionesTren = getPeticionesTrenes([fecha], trayecto, false)
			} else if (opcion == 1) { //proximos tres dias
				ofertas = true
				fecha = new Date().clearTime() + 2
				def listaFechas = [fecha]
				15.times {
					fecha = fecha.next()
					listaFechas << fecha
				}
				peticionesTren = getPeticionesTrenes(listaFechas, trayecto, true)
			}
		} else {
			trayecto = Trayecto.list()[0]
			fecha = new Date().clearTime() + 2
			def fechas = []
			3.times { 
				fechas << fecha ++
			}
			peticionesTren = getPeticionesTrenes(fechas, trayecto, false)
		}
		actionService.consultaTrenes(getAuthenticatedUser(), trayecto, fecha, ofertas)
		[trenes: peticionesTren, ofertas: ofertas, trayecto: trayecto]
	}

	def proximos() {
		chain (action: "trenes", params: [trayecto: params.trayecto, opcion: 1])
	}
	
	/**
	 * This method id only needed for setting the lastURI variable 
	 * into the current session
	 * 
	 * @return redirect to reserva action
	 */
	def detalle() {
		setLastURI()
		redirect action: 'reserva', params: params
	}
	
	@Secured(['ROLE_USER'])
	def reserva() {
		Tren tren = Tren.read(params.id)
		PeticionesTren peticionesTren = peticionesService.peticionesTren(tren)
		List<Date> fechasSugeridas 
		def hoy = new Date().clearTime()
		if (tren.salida < hoy + 2) {
			response.status = 404
			return
		} else if (tren.salida > hoy + 2 )
			fechasSugeridas = [tren.salida -1, tren.salida, tren.salida +1]
		else
			fechasSugeridas = [tren.salida, tren.salida +1]
		List<PeticionesTren> pt = getPeticionesTrenes(fechasSugeridas, tren.trayecto, false)
		List<PeticionesTren> sugeridos = []
		pt.each {
			if (it.oferta && it.oportunidad && it.tren.id != tren.id) {
				sugeridos << it
			}
		}
		def doPayment = grailsApplication.config.cmdt.dopayment
		def precio = Precio.findByTrayecto(tren.trayecto)
		
		actionService.seleccionTren(getAuthenticatedUser(), tren.toString())

		def model = [peticionesTren: peticionesTren, sugeridos: sugeridos, user: getAuthenticatedUser(), doPayment: doPayment, precio: precio]
	}
	
	@Secured(['ROLE_USER'])
	def peticion() {
	}
	
	@Secured(['ROLE_ADMIN'])
	def crearPeticion() {
		if (!params.trenId || !params.user) {
			return [trayectos: Trayecto.list(), users: User.list()]
		}

		Tren tren = Tren.read(params.trenId)
		def user = User.read(params.user)
		if (!params.noPeticion) {
			Peticion peticion = new Peticion(salida: tren.salida, 
											user: user, 
											trayecto: tren.trayecto, 
											estado: EstadoPeticion.A_LA_ESPERA)
			peticion.save(flush: true)
			println peticion.errors
		}
		if (params.notificar) {
			notificacionesService.nuevaReserva(tren, user)
		}
		flash.message = 'Creada'
		[trayectos: Trayecto.list(), users: User.list()]
	}

	@Secured(['ROLE_ADMIN'])
	def trenesCrearPeticion() {
		def Date fecha
		def List<PeticionesTren> peticionesTren = []
		def trayecto = Trayecto.read(params.trayecto)
		def dateFormat = new SimpleDateFormat("dd/MM/yyyy")
		fecha = dateFormat.parse(params.fecha).clearTime()
		if (fecha >= new Date().clearTime())
			peticionesTren = getPeticionesTrenes([fecha], trayecto, false)
		[trenes: peticionesTren]
	}

	@Secured(['ROLE_USER'])
	def cancel() {
		Tren tren = Tren.read(params.id)
		def user = getAuthenticatedUser()
	}
	
	private setLastURI() {
		session['lastURL'] = actionUri
		if (params.id)
			session['lastURL'] += '/' + params.id
		if (request.getQueryString())
			session['lastURL'] += '?' + request.getQueryString()
		log.debug("Last URL: " + session['lastURL'])
	}

	private List<PeticionesTren> getPeticionesTrenes(List<Date> fechas, Trayecto trayecto, boolean ofertas) {
		List<PeticionesTren> peticionesTren = []
		List<Tren> trenesDia = trenesService.buscarTrenes(fechas, trayecto)
		trenesDia.each { Tren tren ->
			if (ofertas) {
				PeticionesTren pt = peticionesService.peticionesTren(tren)
				if (pt.isOferta())
					peticionesTren << pt 
			} else 
				peticionesTren << peticionesService.peticionesTren(tren)
		}
		return peticionesTren
	}

}
