package compartirmesadetren

import grails.plugins.springsecurity.ui.AbstractS2UiController;
import org.codehaus.groovy.grails.plugins.springsecurity.NullSaltSource
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import grails.plugins.springsecurity.Secured

class CambiarPasswordController extends AbstractS2UiController {

	// override default value from base class
	static defaultAction = 'index'

	// override default value from base class
	static allowedMethods = [register: 'POST']

	def mailService
	def messageSource
	def saltSource

	@Secured(['ROLE_USER'])
	def index = { ResetPasswordCommand command ->
		
		if (!request.post) {
			return [command: new ResetPasswordCommand()]
		}

		command.username = springSecurityService.getCurrentUser()
		command.validate()

		if (command.hasErrors()) {
			return [command: command]
		}

		String salt = saltSource instanceof NullSaltSource ? null : registrationCode.username

		def user = lookupUserClass().findByUsername(command.username)
		user.password = springSecurityUiService.encodePassword(command.password, salt)
		user.save(flush: true)

		springSecurityService.reauthenticate command.username

		flash.message = message(code: 'spring.security.ui.resetPassword.success')

		def conf = SpringSecurityUtils.securityConfig
		String postResetUrl = conf.ui.register.postResetUrl ?: conf.successHandler.defaultTargetUrl
		redirect uri: postResetUrl
	}

	static final passwordValidator = { String password, command ->
		if (command.username && command.username.equals(password)) {
			return 'command.password.error.username'
		}

		if (!checkPasswordMinLength(password, command) ||
		    !checkPasswordMaxLength(password, command) ||
		    !checkPasswordRegex(password, command)) {
			return 'command.password.error.strength'
		}
	}

	static boolean checkPasswordMinLength(String password, command) {
		def conf = SpringSecurityUtils.securityConfig

		int minLength = conf.ui.password.minLength instanceof Number ? conf.ui.password.minLength : 8

		password && password.length() >= minLength
	}

	static boolean checkPasswordMaxLength(String password, command) {
		def conf = SpringSecurityUtils.securityConfig

		int maxLength = conf.ui.password.maxLength instanceof Number ? conf.ui.password.maxLength : 64

		password && password.length() <= maxLength
	}

	static boolean checkPasswordRegex(String password, command) {
		def conf = SpringSecurityUtils.securityConfig

		String passValidationRegex = conf.ui.password.validationRegex ?:
				'^.*(?=.*\\d)(?=.*[a-zA-Z]).*$'

		password && password.matches(passValidationRegex)
	}

	static final password2Validator = { value, command ->
		if (command.password != command.password2) {
			return 'command.password2.error.mismatch'
		}
	}
}

class ResetPasswordCommand {
	String username
	String password
	String password2

	static constraints = {
		username nullable: false
		password blank: false, nullable: false, validator: RegisterController.passwordValidator
		password2 validator: RegisterController.password2Validator
	}
}
