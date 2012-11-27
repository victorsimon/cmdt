package compartirmesadetren
import grails.plugins.springsecurity.Secured

class SeleccionInicialController {

	static defaultAction = "trayectos"
	def beforeInterceptor = [action: this.&setLastURI]
	def trenesService
	def peticionesService
		
	def trayectos(Integer opcion) {
		def Date fecha
		if (params?.time)
			fecha = new Date(params.time.toLong())
		else
			fecha = params.fecha
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
		[trayectos: Trayecto.list(), trayecto: trayecto, fecha: fecha, trenes: peticionesTren]
	}

	def proximos() {
		def Date fecha = params.fecha
		chain (action: "trayectos", params: [trayecto: params.trayecto, time : fecha.getTime(), opcion: 1])
	}
	
	def detalle() {
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
		def model = [peticionesTren: peticionesTren, sugeridos: sugeridos]
	}
	
	@Secured(['ROLE_USER'])
	def peticion() {
		Tren tren = Tren.get(params.id)
		Peticion peticion = new Peticion(salida: tren.salida, user: getAuthenticatedUser())
		peticion.save(flush: true)
	}
	
	private setLastURI() {
		session['lastURL'] = actionUri
		if (params.id)
			session['lastURL'] += '/' + params.id
		if (request.getQueryString())
			session['lastURL'] += '?' + request.getQueryString()
		println "Last URL: " + session['lastURL']
	}

	private List<PeticionesTren> getPeticionesTrenes(List<Date> fechas, Trayecto trayecto) {
		List<PeticionesTren> peticionesTren = []
		List<Tren> trenesDia = trenesService.buscarTrenes(fechas, trayecto)
		trenesDia.each {
			peticionesTren << peticionesService.peticionesTren(it)
		}
		return peticionesTren
	}

}
