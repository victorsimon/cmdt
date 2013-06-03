package compartirmesadetren
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class ComentarioController {
	
	def scaffold = true

	def actionService
	def mailService
	def grailsApplication

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
		actionService.nuevoComentario(user)

		println command.texto + "<br/> USER:" + user?.email
		try {
			mailService.sendMail {
				to grailsApplication.config.grails.mail.contact
				subject 'COMENTARIO'
				html command.texto
			}
		}
		catch(Exception e) {
			flash.message = '*'
		}
		
		flash.message += "Tu comentario ha sido guardado correctamente" 
	}
	
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def viajerosFrecuentes (Comentario command) {
		command.texto = 'VIAJEROS FRECUENTES <br/>' + command.texto
		enviar(command)
	}
}