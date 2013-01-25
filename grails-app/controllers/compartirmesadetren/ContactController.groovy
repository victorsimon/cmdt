package compartirmesadetren

import com.megatome.grails.RecaptchaService

class ContactController {

	// override default value from base class
	static defaultAction = 'index'

	def springSecurityService
	def mailService
	def grailsApplication
	def recaptchaService
	
	def index() {
		def copy = [:] + (flash.chainedParams ?: [:])
		def ec = new EmailCommand()
		def lock = false
		if (params.subject) {
			ec.subject = params.subject
			def user = springSecurityService.currentUser
			if (user) {
				ec.user = user
				ec.responseEmail = user.email
			}
			lock = true
		}
		[command: ec, lock: lock]
	}
	
	def form(EmailCommand command) {
		
		if (!command.validate()) {
			return render (view: 'index', model: [command: command])
		}

		if (!recaptchaService.verifyAnswer(session, request.getRemoteAddr(), params)) {
			return render (view: 'index', model: [command: command])
		}
		
		recaptchaService.cleanUp(session)

		mailService.sendMail {
			to grailsApplication.config.grails.mail.contact
			subject command.subject
			html command.body + "<BR/><HR/> RESPONSE E-MAIL: " + command.responseEmail
		}
		
		render view: 'index', model: [emailSent: true]

	}
}

class EmailCommand {
	User user
	String subject
	String body
	String responseEmail
	
	static constraints = {
		user nullable: true
		subject nullable: false, blank: false
		body nullable: false, blank: false 
		responseEmail blank: false, nullable: false, email: true
	}

}