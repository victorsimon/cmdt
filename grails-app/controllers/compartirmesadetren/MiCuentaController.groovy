package compartirmesadetren

import grails.plugins.springsecurity.Secured

class MiCuentaController {
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def springSecurityService
	
	@Secured(['ROLE_USER'])
	def index() {
		redirect(action: "show", params: params)
	}
	
	@Secured(['ROLE_USER'])
	def show() {
		def userInstance = springSecurityService.currentUser

		[userInstance: userInstance]		
	}
	
	@Secured(['ROLE_USER'])
	def listRequest(Long id) {
		def userInstance = springSecurityService.currentUser
		def peticiones = Peticion.findAllByUserAndSalidaGreaterThan(userInstance, new Date(), params)
		def total = peticiones?.size()
		render (view: "list", model: [peticionInstanceList: peticiones, peticionInstanceTotal: total])
	}

	@Secured(['ROLE_USER'])
	def listClosed(Long id) {
		def userInstance = springSecurityService.currentUser
		def peticiones = Peticion.findAllByUserAndSalidaLessThan(userInstance, new Date(), params)
		def total = peticiones?.size()
		render (view: "list", model: [peticionInstanceList: peticiones, peticionInstanceTotal: total])
	}
}
