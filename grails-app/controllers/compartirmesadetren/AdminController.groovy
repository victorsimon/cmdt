package compartirmesadetren

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class AdminController {
	
	static defaultAction = "index"

	def index() {
		render (view: "index", 
			model: [
				peticiones: Peticion.list(sort: "salida", order: "asc"),
				totales: new TotalesCommand()])
	}

	def todas() {
		render (template: "peticiones", 
			model: [
				peticiones: Peticion.list(sort: "salida", order: "asc"), 
				totales: new TotalesCommand()])
	}

	def pendientes() {
		def peticiones = Peticion.findAllBySalidaGreaterThanEquals(new Date().clearTime(), 
			[sort: "salida", order: "asc"])
		render (template: "peticiones", model: [peticiones: peticiones,
			totales: new TotalesCommand()])
	}

	def pasadas() {
		def peticiones = Peticion.findAllBySalidaLessThan(new Date().clearTime(), 
			[sort: "salida", order: "asc"])
		render (template: "peticiones", model: [peticiones: peticiones,
			totales: new TotalesCommand()])
	}

	def enEspera() {
		porEstado("A_LA_ESPERA", params.fecha)
	}

	def enProceso() {
		porEstado(EstadoPeticion.BAJO_GESTION, params.fecha)
	}

	def canceladas() {
		porEstado(EstadoPeticion.CANCELADA, params.fecha)
	}

	def anuladas() {
		porEstado(EstadoPeticion.ANULADA, params.fecha)
	}

	def cerradas() {
		porEstado(EstadoPeticion.CERRADA, params.fecha)
	}

	def porEstado() {
		def peticiones = params.fecha?:Peticion.findAllByEstado(
			params.estado, 
			[sort: "salida", order: "asc"])
		render (template: "peticiones", model: [peticiones: peticiones,
			totales: new TotalesCommand()])
	}

	def detalle() {
		def peticion = Peticion.read(params.id)
		def plantilla
		switch(peticion.estado) {
			case EstadoPeticion.A_LA_ESPERA:
				plantilla = "detalle_espera"
			break
			case EstadoPeticion.BAJO_GESTION:
				plantilla = "detalle_espera"
			break
		}
		render (template: "detalle", model: [peticion: peticion])
	}
}

class TotalesCommand {
	def todas
	def pendientes
	def pasadas
	def enEspera
	def enProceso
	def canceladas
	def anuladas
	def cerradas

	public TotalesCommand() {
		todas = Peticion.count()
		pendientes = Peticion.countBySalidaGreaterThanEquals(new Date().clearTime())
		pasadas = Peticion.countBySalidaLessThan(new Date().clearTime()) 
		enEspera = countByEstado (EstadoPeticion.A_LA_ESPERA) 
		enProceso = countByEstado (EstadoPeticion.BAJO_GESTION) 
		canceladas = countByEstado (EstadoPeticion.CANCELADA) 
		anuladas = countByEstado (EstadoPeticion.ANULADA) 
		cerradas = countByEstado (EstadoPeticion.CERRADA) 
	}

	private int countByEstado(estado) {
		return Peticion.countByEstado(estado) 
	}
}