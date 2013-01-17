package compartirmesadetren

import grails.plugins.springsecurity.Secured
import org.springframework.web.servlet.ModelAndView

@Secured(['ROLE_ADMIN'])
class PeticionController {
    def scaffold = true
}
