package compartirmesadetren

class ContactController {

	// override default value from base class
	static defaultAction = 'index'

	def springSecurityService
	def mailService
	def grailsApplication
	
	def index() {
		def copy = [:] + (flash.chainedParams ?: [:])
		copy.remove 'controller'
		copy.remove 'action'
		def lock = false
		if (params.subject) {
			copy.subject = params.subject
			def user = springSecurityService.currentUser
			if (user) {
				copy.user = user
				copy.responseEmail = user.email
			}
			lock = true
		}
		[command: new EmailCommand(copy), lock: lock]
	}
	
	def form(EmailCommand command) {
		if (command.subject) {
			//command = new EmailCommand()
			def user = springSecurityService.currentUser
			if (user) {
				command.user = user
				command.responseEmail = user.email
			}
			return render (view: 'index', model: [command: command])
		}
		
		if (!command.validate()) {
			return render (view: 'index', model: [command: command])
		}
		
		/*println grailsApplication.config.grails.mail.contact
		println command.responseEmail
		println command.subject
		println command.body*/
		
		mailService.sendMail {
			to grailsApplication.config.grails.mail.contact
			subject command.subject
			html command.body + "<BR/> RESPONSE E-MAIL: " + command.responseEmail
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
		subject nullable: false
		body nullable: false, blank: false 
		responseEmail blank: false, nullable: false, email: true
	}

}