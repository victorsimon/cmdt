package compartirmesadetren
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class TrenObjetivoController {
	def scaffold = true
}
