package compartirmesadetren

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured
import org.springframework.web.servlet.ModelAndView

@Secured(['ROLE_ADMIN'])
class UserController {
    def scaffold = true

}
