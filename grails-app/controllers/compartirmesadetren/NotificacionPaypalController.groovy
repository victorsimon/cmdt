package compartirmesadetren
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class NotificacionPaypalController {
	def scaffold = true
}
