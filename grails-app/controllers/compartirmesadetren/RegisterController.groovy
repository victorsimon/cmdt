package compartirmesadetren

class RegisterController extends grails.plugins.springsecurity.ui.RegisterController {
}

class RegisterCommand {

	String username
	String email
	String password
	String password2
	String phoneNumber

	def grailsApplication

	static constraints = {
		username blank: false, nullable: false, validator: { value, command ->
			if (value) {
				def User = command.grailsApplication.getDomainClass(
					SpringSecurityUtils.securityConfig.userLookup.userDomainClassName).clazz
				if (User.findByUsername(value)) {
					return 'registerCommand.username.unique'
				}
			}
		}
		email blank: false, nullable: false, email: true
		password blank: false, nullable: false, validator: RegisterController.passwordValidator
		password2 validator: RegisterController.password2Validator
		phoneNumber minSize: 9
	}
}
