package compartirmesadetren

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_USER'])
class MiCuentaController {
	
	// override default value from base class
	static defaultAction = 'show'

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def springSecurityService
	
	def show() {
		def userInstance = springSecurityService.currentUser

		[userInstance: userInstance]		
	}
	
	def listRequest(Long id) {
		def userInstance = springSecurityService.currentUser
		def peticiones = peticionesToCommand(Peticion.findAllByUserAndSalidaGreaterThan(userInstance, new Date(), params))
		def total = peticiones?.size()
		render (view: "list", model: [peticionInstanceList: peticiones, peticionInstanceTotal: total, peticionesTittle: 'Peticiones abiertas'])
	}

	def listClosed(Long id) {
		def userInstance = springSecurityService.currentUser
		def peticiones = peticionesToCommand(Peticion.findAllByUserAndSalidaLessThan(userInstance, new Date(), params))
		def total = peticiones?.size()
		render (view: "list", model: [peticionInstanceList: peticiones, peticionInstanceTotal: total, peticionesTittle: 'Historico de peticiones'])
	}
	
	def cambiarEmail(CambiarEmailCommand command) {
	
		if (!request.post) {
			return [command: new CambiarEmailCommand()]
		}

		command.username = springSecurityService.getCurrentUser()
		command.validate()

		if (command.hasErrors()) {
			return [command: command]
		}
		
		def user = User.findByUsername(command.username)
		user.email = command.email
		user.save(flush: true)

		redirect (action: 'show')
	}

	private peticionesToCommand(peticiones) {
		def commandList = []
		peticiones.each() {
			commandList << new PeticionCommand(it)
		}
		return commandList
	}
}

class PeticionCommand {
	Date salida
	Trayecto trayecto
	def plazas
	def estado

	public PeticionCommand(peticion) {
		this.salida = peticion.salida
		this.trayecto = peticion.trayecto
		this.plazas = peticion.plazas
		this.estado = estadoToInfo peticion.estado
	}

	private final estadoToInfo(estado) {
		def e = estado
		switch(estado) {
			case EstadoPeticion.IGNORAR:
			case EstadoPeticion.CERRADA:
				e = "Finalizada"				
			break
			case EstadoPeticion.A_LA_ESPERA:
			case EstadoPeticion.BAJO_GESTION:
			case EstadoPeticion.VIGILAR:
			case EstadoPeticion.RESERVAR:
				e = "Bajo gestión"
			break
			case EstadoPeticion.COBRAR:
				e = "Pendiente de pago"
			break
			case EstadoPeticion.COMPRAR:
			case EstadoPeticion.ENVIAR:
				e = "Preparando billete"
			break
			case EstadoPeticion.COBRAR_FIANZA:
			case EstadoPeticion.ANULADA:
				e = "Anulada"
			break
			case EstadoPeticion.CANCELAR_FIANZA:
			case EstadoPeticion.CANCELADA:
				e = "Cancelada"
			break
		}
		return e
	}
}

class CambiarEmailCommand {
	String username
	String email
	String email2

	static constraints = {
		username nullable: false
        email blank: false, email: true
        email2 nullable: true, blank: true, validator: { email2, command ->
            if (command.email != email2) {
                return 'OAuthCreateAccountCommand.password.error.mismatch'
            }
        }
	}

}
