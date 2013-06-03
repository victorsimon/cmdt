package compartirmesadetren
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class ConsultaRenfeController {

	def trenesService

	def index() {
		[fecha: new Date(), trayectos: Trayecto.findAll()]
	}

	def historial() {
		def datos
		Date fecha = Date.parse('dd/MM/yyyy', params?.fecha)
		def trayecto = params?.trayecto
		if (fecha && trayecto) {
			datos = DatosRenfe.findAllBySalidaAndTrayecto(fecha.clearTime(), Trayecto.read(trayecto))
		} 
		println datos
		[datos: datos]
	}

	def buscar() {
		def datos
		Date fecha = Date.parse('dd/MM/yyyy', params?.fecha)
		def trayecto = params?.trayecto
		if (fecha && trayecto) {
			datos = trenesService.consultarTrenesDisponibles(Trayecto.read(trayecto), fecha)
		} 
		println datos
		[datos: datos]
	}
}
