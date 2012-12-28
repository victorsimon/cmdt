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
		def peticiones = Peticion.findAllByUserAndSalidaGreaterThan(userInstance, new Date(), params)
		def total = peticiones?.size()
		render (view: "list", model: [peticionInstanceList: peticiones, peticionInstanceTotal: total])
	}

	def listClosed(Long id) {
		def userInstance = springSecurityService.currentUser
		def peticiones = Peticion.findAllByUserAndSalidaLessThan(userInstance, new Date(), params)
		def total = peticiones?.size()
		render (view: "list", model: [peticionInstanceList: peticiones, peticionInstanceTotal: total])
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
