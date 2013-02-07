package compartirmesadetren
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class UserActionController {
	def scaffold = true
}
