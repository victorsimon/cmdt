package compartirmesadetren
import org.springframework.web.servlet.ModelAndView
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class EstacionController {
	def scaffold = true
}
