package compartirmesadetren
import grails.plugins.springsecurity.Secured
import java.text.DateFormat
import java.text.SimpleDateFormat
import groovyx.gpars.GParsPool

class SeleccionInicialController {

	static defaultAction = "trayectos"
	def trenesService
	def peticionesService
	def grailsApplication
	def mailService
	
	def trayectos(Integer opcion) {
		setLastURI()
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
			trayecto = Trayecto.read(params.trayecto)
			if (!opcion) {
				peticionesTren = getPeticionesTrenes([fecha], trayecto, false)
			} else if (opcion == 1) { //proximos tres dias
				peticionesTren = getPeticionesTrenes([new Date() + 2, new Date() +3, new Date() +4, new Date() +5, new Date() +6, new Date() +7, new Date() +8], trayecto, true)
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
		def ofertas = false
		if (params.trayecto) {
			trayecto = Trayecto.read(params.trayecto)
			if (!opcion) {
				if (fecha > new Date() + 1)
					peticionesTren = getPeticionesTrenes([fecha], trayecto, false)
			} else if (opcion == 1) { //proximos tres dias
				ofertas = true
				peticionesTren = getPeticionesTrenes([new Date() + 2, new Date() +3, new Date() +4, new Date() +5, new Date() +6, new Date() +7, new Date() +8], trayecto, true)
			}
		}
		[trenes: peticionesTren, ofertas: ofertas, trayecto: trayecto]
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
		setLastURI()
		redirect action: 'reserva', params: params
	}
	
	@Secured(['ROLE_USER'])
	def reserva() {
		Tren tren = Tren.read(params.id)
		PeticionesTren peticionesTren = peticionesService.peticionesTren(tren)
		List<Date> fechasSugeridas = [tren.salida -1, tren.salida, tren.salida +1]
		List<PeticionesTren> pt = getPeticionesTrenes(fechasSugeridas, tren.trayecto, false)
		List<PeticionesTren> sugeridos = []
		pt.each {
			if (it.oferta && it.oportunidad && it.tren.id != tren.id) {
				sugeridos << it
			}
		}
		def doPayment = grailsApplication.config.cmdt.dopayment
		def precio = Precio.findByTrayecto(tren.trayecto)

		def model = [peticionesTren: peticionesTren, sugeridos: sugeridos, user: getAuthenticatedUser(), doPayment: doPayment, precio: precio]
	}
	
	@Secured(['ROLE_USER'])
	def peticion() {
		Tren tren = Tren.read(params.id)
		def user = getAuthenticatedUser()
		Peticion peticion = new Peticion(salida: tren.salida, user: user, trayecto: tren.trayecto, estado: EstadoPeticion.A_LA_ESPERA)
		peticion.save(flush: true)
		
		GParsPool.withPool {
			//Mail para la gestion
			GParsPool.executeAsyncAndWait ({
				mailService.sendMail {
					to grailsApplication.config.grails.mail.contact
					subject "Nueva reserva " + tren.toString() + " " + tren.trayecto
					html tren.toString() + "|" + tren.trayecto + "|" + user.username + "|" + user.email
				}
			})
			
			//Mail para el usuario
			String mailContent = g.render(template:"mailReserva", model:[user: user, tren: tren])
			GParsPool.executeAsyncAndWait ({
				mailService.sendMail {
					to user.email
					subject "Reserva: " + tren.toString() + " " + tren.trayecto + " - www.compartirmesadetren.com"
					html mailContent
				}
			})
		}
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
