package compartirmesadetren
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class ComentarioController {
	
	def scaffold = true

	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def enviar (Comentario command) {
		if (!command.validate()) {
			return
		}
		def user = getAuthenticatedUser()		

		if (user) {
			command.user = user
		}
		command.save()
		
		flash.message = "Tu comentario ha sido enviado correctamente" 
	}
	
}