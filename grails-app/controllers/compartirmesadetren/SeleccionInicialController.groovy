package compartirmesadetren
import grails.plugins.springsecurity.Secured
import java.text.DateFormat
import java.text.SimpleDateFormat

class SeleccionInicialController {

	static defaultAction = "trayectos"
	def beforeInterceptor = [action: this.&setLastURI]
	def trenesService
	def peticionesService
	def grailsApplication
	
	def trayectos(Integer opcion) {
		def Date fecha
		if (params?.time)
			fecha = new Date(params.time.toLong())
		else if (params.fecha) {
			def dateFormat = new SimpleDateFormat("dd/MM/yyyy")
			fecha = dateFormat.parse(params.fecha)
		}
		def Trayecto trayecto
		List<PeticionesTren> peticionesTren = []
		if (params.trayecto) {
			trayecto = Trayecto.get(params.trayecto)
			if (!opcion) {
				peticionesTren = getPeticionesTrenes([fecha], trayecto)
			} else if (opcion == 1) { //proximos tres dias
				peticionesTren = getPeticionesTrenes([new Date() + 2, new Date() +3], trayecto)
			}
		}
		[trayectos: Trayecto.list(), trayecto: trayecto, fecha: fecha?.format("dd/MM/yyyy"), trenes: peticionesTren]
	}
	
	def trenes(Integer opcion) {
		def Date fecha
		if (params?.time)
			fecha = new Date(params.time.toLong())
		else if (params.fecha) {
			def dateFormat = new SimpleDateFormat("dd/MM/yyyy")
			fecha = dateFormat.parse(params.fecha)
		}
		def Trayecto trayecto
		List<PeticionesTren> peticionesTren = []
		if (params.trayecto) {
			trayecto = Trayecto.get(params.trayecto)
			if (!opcion) {
				peticionesTren = getPeticionesTrenes([fecha], trayecto)
			} else if (opcion == 1) { //proximos tres dias
				peticionesTren = getPeticionesTrenes([new Date() + 2, new Date() +3], trayecto)
			}
		}
		[trenes: peticionesTren]
	}

	def proximos() {
		def dateFormat = new SimpleDateFormat("dd/MM/yyyy")
		def fecha = dateFormat.parse(params.fecha)
		chain (action: "trenes", params: [trayecto: params.trayecto, time : fecha.getTime(), opcion: 1])
	}
	
	/**
	 * This method id only needed for setting the lastURI variable 
	 * into the current session
	 * 
	 * @return redirect to reserva action
	 */
	def detalle() {
		redirect action: 'reserva', params: params
	}
	
	@Secured(['ROLE_USER'])
	def reserva() {
		Tren tren = Tren.get(params.id)
		PeticionesTren peticionesTren = peticionesService.peticionesTren(tren)
		List<Date> fechasSugeridas = [tren.salida -1, tren.salida, tren.salida +1]
		List<PeticionesTren> pt = getPeticionesTrenes(fechasSugeridas, tren.trayecto)
		List<PeticionesTren> sugeridos = []
		pt.each {
			if (it.oportunidad && it.tren.id != tren.id) {
				sugeridos << it
			}
		}
		def doPayment = grailsApplication.config.cmdt.dopayment

		def model = [peticionesTren: peticionesTren, sugeridos: sugeridos, user: getAuthenticatedUser(), doPayment: doPayment]
	}
	
	@Secured(['ROLE_USER'])
	def peticion() {
		println "Creando peticion " + params.id
		Tren tren = Tren.get(params.id)
		Peticion peticion = new Peticion(salida: tren.salida, user: getAuthenticatedUser(), trayecto: tren.trayecto, estado: EstadoPeticion.A_LA_ESPERA)
		peticion.save(flush: true)
		println peticion.errors
	}
	
	private setLastURI() {
		session['lastURL'] = actionUri
		if (params.id)
			session['lastURL'] += '/' + params.id
		if (request.getQueryString())
			session['lastURL'] += '?' + request.getQueryString()
		log.debug("Last URL: " + session['lastURL'])
	}

	private List<PeticionesTren> getPeticionesTrenes(List<Date> fechas, Trayecto trayecto) {
		List<PeticionesTren> peticionesTren = []
		List<Tren> trenesDia = trenesService.buscarTrenes(fechas, trayecto)
		trenesDia.each { Tren tren ->
			peticionesTren << peticionesService.peticionesTren(tren)
		}
		return peticionesTren
	}

}
