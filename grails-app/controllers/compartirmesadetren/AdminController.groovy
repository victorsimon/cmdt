package compartirmesadetren

import grails.plugins.springsecurity.Secured
import grails.converters.*

@Secured(['ROLE_ADMIN'])
class AdminController {
	
	static defaultAction = "index"

	def notificacionesService

	def index() {
		def peticiones = Peticion.findAllBySalidaGreaterThanEquals(new Date().clearTime(), 
			[sort: "salida", order: "desc"])
		render (view: "index", 
			model: [
				peticiones: peticiones,
				totales: new TotalesCommand()])
	}

	def edit(Long id) {
		render (template: "edit", model: [
			peticion: Peticion.get(id), 
			trayectos: Trayecto.findAll(),
			success: false])
	}

	def save() {
		def peticion = Peticion.get(params.id)
		def salida = new Date().parse("dd/MM/yyyy HH:mm", params.fecha + " " + params.hora)
		peticion.salida = salida
		peticion.estado = params.estado
		peticion.trayecto = Trayecto.read(params.trayecto)
		peticion.save(flush: true)
		render (template: "edit", model: [
			peticion: peticion, 
			trayectos: Trayecto.findAll(),
			success: true])
	}

	def reservas() {
		def peticiones = Peticion.findAllBySalidaGreaterThanEquals(new Date().clearTime(), 
			[sort: "salida", order: "desc"])
		render (template: "reservas", 
			model: [
				peticiones: peticiones, 
				totales: new TotalesCommand()])
	}

	def todas() {
		render (template: "peticiones", 
			model: [
				peticiones: Peticion.list(sort: "salida", order: "desc"), 
				totales: new TotalesCommand()])
	}

	def pendientes() {
		def peticiones = Peticion.findAllBySalidaGreaterThanEquals(new Date().clearTime(), 
			[sort: "salida", order: "desc"])
		render (template: "peticiones", model: [peticiones: peticiones,
			totales: new TotalesCommand()])
	}

	def pasadas() {
		def peticiones = Peticion.findAllBySalidaLessThan(new Date().clearTime(), 
			[sort: "salida", order: "desc"])
		render (template: "peticiones", model: [peticiones: peticiones,
			totales: new TotalesCommand()])
	}

	def porEstado() {
		def peticiones = params.fecha?:Peticion.findAllByEstado(
			params.estado, 
			[sort: "salida", order: "desc"])
		render (template: "peticiones", model: [peticiones: peticiones,
			totales: new TotalesCommand()])
	}

	def detalle() {
		def peticion = Peticion.read(params.id)
		def acciones = [[[:]]]
		def estado = peticion.estado
		switch(estado) {
			case EstadoPeticion.A_LA_ESPERA:
				acciones = [
								[
									[accion: "gestionar", texto: "Gestionar", help: "Normalmente, se procede a gestionar una  petici&oacute;n cuando se alcanzan las plazas necesarias. Una vez bajo gesti&oacute;n, se comprobara la disponibilidad de billetes y la caducidad de forma autom&aacute;tica."], 
									[accion: "gestionarTren", texto: "Gestionar tren", help: "Pasa a gesti&oacute;n todas las peticiones de un tren. Normalmente, se procede a gestionar una  petici&oacute;n cuando se alcanzan las plazas necesarias. Una vez bajo gesti&oacute;n, se comprobara la disponibilidad de billetes y la caducidad de forma autom&aacute;tica."]
								],
								[
									[accion: "anular", texto: "Anular peticion", help: "Se anula a petici&oacute;n del usuario. Pasa a pendiente de cobrar la fianza. Esta acc&oacute;n se notifica al usuario."], 
									[accion: "cancelar", texto: "Cancelar", help: "Normalmente, se cancela por caducidad. Pasa a pendiente de cancelar la fianza. Esta acc&oacute;n se notifica al usuario."]
								]
							]
			break
			case EstadoPeticion.BAJO_GESTION:
				acciones = [
								[
									[accion: "anular", texto: "Anular peticion", help: "Se anula a petici&oacute;n del usuario. Pasa a pendiente de cobrar la fianza. Esta acc&oacute;n se notifica al usuario."], 
									[accion: "cancelar", texto: "Cancelar", help: "Normalmente, se cancela por caducidad. Pasa a pendiente de cancelar la fianza. Esta acc&oacute;n se notifica al usuario."]
								],
								[
									[accion: "esperando", texto: "Devolver a esperando", help: "Cambiar el estado a esperando. Si es por una anulaci&oacute;n, hay que anular primero. Si no se promocionar&aacute; autom&aacute;ticamente a gestionandose por n&uacute;mero de plazas."], 
									[accion: "esperandoTren", texto: "Devolver tren a esperando", help: "Cambiar el estado a esperando a todo el tren. Si es por una anulaci&oacute;n, hay que anular primero. Si no se promocionar&aacute; autom&aacute;ticamente a gestionandose por n&uacute;mero de plazas."]
								]
							]
			break
			case EstadoPeticion.VIGILAR:
				acciones = [
								[
									[accion: "anular", texto: "Anular peticion", help: "Se anula a petici&oacute;n del usuario. Pasa a pendiente de cobrar la fianza. Esta acc&oacute;n se notifica al usuario."], 
									[accion: "cancelar", texto: "Cancelar", help: "Normalmente, se cancela por caducidad. Pasa a pendiente de cancelar la fianza. Esta acc&oacute;n se notifica al usuario."]
								],
								[
									[accion: "esperando", texto: "Devolver a esperando", help: "Cambiar el estado a esperando. Si es por una anulaci&oacute;n, hay que anular primero. Si no se promocionar&aacute; autom&aacute;ticamente a gestionandose por n&uacute;mero de plazas."], 
									[accion: "esperandoTren", texto: "Devolver tren a esperando", help: "Cambiar el estado a esperando a todo el tren. Si es por una anulaci&oacute;n, hay que anular primero. Si no se promocionar&aacute; autom&aacute;ticamente a gestionandose por n&uacute;mero de plazas."]
								]
							]
			break
			case EstadoPeticion.RESERVAR:
				acciones = [
								[
									[accion: "cobrar", texto: "Cobrar", help: "Hemos reservado los billetes, por lo que marcamos la petici&oacute;n como pendiente de cobrar."],	
									[accion: "cobrarTren", texto: "Cobrar el tren", help: "Hemos reservado los billetes, por lo que marcamos todas las peticiones del tren como pendiente de cobrar."]
								],
								[
									[accion: "vigilar", texto: "Vigilar", help: "Si no hay billetes disponibles, esta opci&oacute;n pone una alarma por si vuelve a haber. Solo para la petici&oacute;n seleccionada."],	
									[accion: "vigilarTren", texto: "Vigilar el tren", help: "Si no hay billetes disponibles, esta opci&oacute;n pone una alarma por si vuelve a haber. Esta opci&oacute;n marca todas las peticiones de este tren;"]
								],
								[
									[accion: "anular", texto: "Anular peticion", help: "Se anula a petici&oacute;n del usuario. Pasa a pendiente de cobrar la fianza. Esta acc&oacute;n se notifica al usuario."], 
									[accion: "cancelar", texto: "Cancelar", help: "Normalmente, se cancela por caducidad. Pasa a pendiente de cancelar la fianza. Esta acc&oacute;n se notifica al usuario."]
								],
								[
									[accion: "esperando", texto: "Devolver a esperando", help: "Cambiar el estado a esperando. Si es por una anulaci&oacute;n, hay que anular primero. Si no se promocionar&aacute; autom&aacute;ticamente a gestionandose por n&uacute;mero de plazas."], 
									[accion: "esperandoTren", texto: "Devolver tren a esperando", help: "Cambiar el estado a esperando a todo el tren. Si es por una anulaci&oacute;n, hay que anular primero. Si no se promocionar&aacute; autom&aacute;ticamente a gestionandose por n&uacute;mero de plazas."]
								]
							]
			break
			case EstadoPeticion.COBRAR:
				acciones = [
								[
									[accion: "comprar", texto: "Comprar", help: "Hemos cobrado los billetes, por lo que marcamos la petici&oacute;n como pendiente de comprar."],	
									[accion: "comprarTren", texto: "Comprar el tren", help: "Hemos cobrado los billetes, por lo que marcamos todas las peticiones del tren como pendiente de comprar."]
								]
							]
			break
			case EstadoPeticion.COMPRAR:
				acciones = [
								[
									[accion: "enviar", texto: "Enviar", help: "Hemos comprado los billetes, por lo que marcamos la petici&oacute;n como pendiente de enviar."],	
									[accion: "enviarTren", texto: "Enviar el tren", help: "Hemos comprado los billetes, por lo que marcamos todas las peticiones del tren como pendiente de enviar."]
								]
							]
			break
			case EstadoPeticion.ENVIAR:
				acciones = [
								[
									[accion: "cerrar", texto: "Cerrar", help: "Hemos finalizado el proceso. Cerramos la transacci&oacute;n."],	
									[accion: "cerrarTren", texto: "Cerrar el tren", help: "Hemos finalizado el proceso. Cerramos todas las transaciones del tren."]
								]
							]
			break
			case EstadoPeticion.CERRADA:
				acciones = [
								[
								]
							]
			break
			case EstadoPeticion.COBRAR_FIANZA:
				acciones = [
								[
									[accion: "anulada", texto: "Fianza cobrada"], 
									[accion: "esperando", texto: "Devolver a esperando"] 
								]
							]
			break
			case EstadoPeticion.CANCELAR_FIANZA:
				acciones = [
								[
									[accion: "cancelada", texto: "Fianza cancelada"], 
									[accion: "esperando", texto: "Devolver a esperando"] 
								]
							]
			break
			case EstadoPeticion.ANULADA:
			case EstadoPeticion.CANCELADA:
				acciones = [
								[
									[accion: "esperando", texto: "Devolver a esperando"], 
								]
							]
			break
		}
		render (template: "detalle", model: [peticion: peticion, acciones: acciones])
	}

	def gestionar() {
		transitarA(params.id, EstadoPeticion.BAJO_GESTION)
		porEstado()
	}

	def gestionarTren() {
		def guia = Peticion.read(params.id)
		def peticionesTren = Peticion.findAllBySalidaAndTrayecto(guia.salida, guia.trayecto)
		def estado = guia.estado
		peticionesTren.each() { peticion ->
			if (peticion.estado == estado) {
				peticion.estado = EstadoPeticion.BAJO_GESTION
				peticion.save(flush: true)
			}
		}
		params.estado = EstadoPeticion.BAJO_GESTION
		porEstado()
	}

	def esperando() {
		transitarA(params.id, EstadoPeticion.A_LA_ESPERA)
		porEstado()
	}

	def esperandoTren() {
		def guia = Peticion.read(params.id)
		def peticionesTren = Peticion.findAllBySalidaAndTrayecto(guia.salida, guia.trayecto)
		def estado = guia.estado
		peticionesTren.each() { peticion ->
			if (peticion.estado == estado) {
				peticion.estado = EstadoPeticion.A_LA_ESPERA
				peticion.save(flush: true)
			}
		}
		params.estado = EstadoPeticion.A_LA_ESPERA
		porEstado()
	}

	def cobrar() {
		transitarA(params.id, EstadoPeticion.COBRAR)
		porEstado()
	}

	def cobrarTren() {
		def guia = Peticion.read(params.id)
		def peticionesTren = Peticion.findAllBySalidaAndTrayecto(guia.salida, guia.trayecto)
		def estado = guia.estado
		peticionesTren.each() { peticion ->
			if (peticion.estado == estado) {
				peticion.estado = EstadoPeticion.COBRAR
				peticion.save(flush: true)
			}
		}
		params.estado = EstadoPeticion.COBRAR
		porEstado()
	}

	def vigilar() {
		println "vigilar"
		transitarA(params.id, EstadoPeticion.VIGILAR)
		addObjetivo(Peticion.get(params.id))
		porEstado()
	}

	def vigilarTren() {
		def guia = Peticion.read(params.id)
		def peticionesTren = Peticion.findAllBySalidaAndTrayecto(guia.salida, guia.trayecto)
		def estado = guia.estado
		def vigilado = false
		peticionesTren.each() { peticion ->
			if (peticion.estado == estado) {
				peticion.estado = EstadoPeticion.VIGILAR
				peticion.save(flush: true)
				if (!vigilado) {
					addObjetivo(peticion)
				}
			}
		}
		params.estado = EstadoPeticion.VIGILAR
		porEstado()
	}

	def comprar() {
		transitarA(params.id, EstadoPeticion.COMPRAR)
		porEstado()
	}

	def comprarTren() {
		def guia = Peticion.read(params.id)
		def peticionesTren = Peticion.findAllBySalidaAndTrayecto(guia.salida, guia.trayecto)
		def estado = guia.estado
		peticionesTren.each() { peticion ->
			if (peticion.estado == estado) {
				peticion.estado = EstadoPeticion.COMPRAR
				peticion.save(flush: true)
			}
		}
		params.estado = EstadoPeticion.COMPRAR
		porEstado()
	}

	def enviar() {
		transitarA(params.id, EstadoPeticion.ENVIAR)
		porEstado()
	}

	def enviarTren() {
		def guia = Peticion.read(params.id)
		def peticionesTren = Peticion.findAllBySalidaAndTrayecto(guia.salida, guia.trayecto)
		def estado = guia.estado
		peticionesTren.each() { peticion ->
			if (peticion.estado == estado) {
				peticion.estado = EstadoPeticion.ENVIAR
				peticion.save(flush: true)
			}
		}
		params.estado = EstadoPeticion.ENVIAR
		porEstado()
	}

	def cerrar() {
		transitarA(params.id, EstadoPeticion.CERRADA)
		porEstado()
	}

	def cerrarTren() {
		def guia = Peticion.read(params.id)
		def peticionesTren = Peticion.findAllBySalidaAndTrayecto(guia.salida, guia.trayecto)
		def estado = guia.estado
		peticionesTren.each() { peticion ->
			if (peticion.estado == estado) {
				peticion.estado = EstadoPeticion.CERRADA
				peticion.save(flush: true)
			}
		}
		params.estado = EstadoPeticion.CERRADA
		porEstado()
	}

	def anular() {
		def peticion = transitarA(params.id, EstadoPeticion.COBRAR_FIANZA)
		notificacionesService.anularReserva(peticion)
		porEstado()
	}

	def cancelar() {
		def peticion = transitarA(params.id, EstadoPeticion.CANCELAR_FIANZA)
		notificacionesService.cancelarReserva(peticion)
		porEstado()
	}

	def anulada() {
		def peticion = transitarA(params.id, EstadoPeticion.ANULADA)
		porEstado()
	}

	def cancelada() {
		def peticion = transitarA(params.id, EstadoPeticion.CANCELADA)
		porEstado()
	}

	private def transitarA(id, estado) {
		def peticion = Peticion.read(id)
		params.estado = peticion.estado
		peticion.estado = estado
		peticion.save(flush: true)
		return peticion
	}

    private addObjetivo(peticion) {
        def salida = peticion.salida.clone()
        salida.clearTime()
        def hora = peticion.salida.format("HH.mm")
        def objetivos = TrenObjetivo.findAllBySalidaAndTrayecto(salida, peticion.trayecto)
        def activo = false
        objetivos.each { objetivo ->
            if (hora == objetivo.hora && objetivo.activo)
                activo = true
        }
        if (!activo) {
            println "Creamos vigilancia para $salida y hora $hora"
            def nuevoObjetivo = new TrenObjetivo(
                salida: salida, trayecto: peticion.trayecto, hora: hora)
            nuevoObjetivo.save(flush: true)
        } else {
            println "Este tren ya esta bajo vigilancia"
        }
    }

    def totales() {
    	def totales = new TotalesCommand()
    	render totales as JSON
    }

}

class TotalesCommand {
	def todas
	def pendientes
	def pasadas
	def enEspera
	def enProceso
	def canceladas
	def cancelarFianza
	def anuladas
	def cobrarFianza
	def cerradas
	def vigilar
	def reservar
	def cobrar
	def comprar
	def enviar
	def ignorar

	public TotalesCommand() {
		todas = Peticion.count()
		pendientes = Peticion.countBySalidaGreaterThanEquals(new Date().clearTime())
		pasadas = Peticion.countBySalidaLessThan(new Date().clearTime()) 
		enEspera = countByEstado (EstadoPeticion.A_LA_ESPERA) 
		enProceso = countByEstado (EstadoPeticion.BAJO_GESTION) 
		canceladas = countByEstado (EstadoPeticion.CANCELADA) 
		cancelarFianza = countByEstado (EstadoPeticion.CANCELAR_FIANZA) 
		anuladas = countByEstado (EstadoPeticion.ANULADA) 
		cobrarFianza = countByEstado (EstadoPeticion.COBRAR_FIANZA) 
		cerradas = countByEstado (EstadoPeticion.CERRADA) 
		vigilar = countByEstado (EstadoPeticion.VIGILAR) 
		reservar = countByEstado (EstadoPeticion.RESERVAR) 
		cobrar = countByEstado (EstadoPeticion.COBRAR) 
		comprar = countByEstado (EstadoPeticion.COMPRAR) 
		enviar = countByEstado (EstadoPeticion.ENVIAR) 
		ignorar = countByEstado (EstadoPeticion.IGNORAR) 
	}

	private int countByEstado(estado) {
		return Peticion.countByEstado(estado) 
	}
}