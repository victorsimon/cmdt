package compartirmesadetren
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class DatosRenfeController {
	def scaffold = true
}
