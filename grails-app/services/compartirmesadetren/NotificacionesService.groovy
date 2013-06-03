package compartirmesadetren

import groovyx.gpars.GParsPool

class NotificacionesService {

	def mailService
	def twitter4jService
	def calendarioService
	def grailsApplication

    def nuevaReserva(tren, user) {
		def ics = calendarioService.createICal(tren)
		GParsPool.withPool {
			//Mail para la gestion
			GParsPool.executeAsyncAndWait ({
				mailService.sendMail {
					multipart true
					to grailsApplication.config.grails.mail.contact
					subject "Nueva reserva " + tren.toString() + " " + tren.trayecto
					html tren.toString() + "|" + tren.trayecto + "|" + user.username + "|" + user.email
					attach "reserva.ics", "text/calendar",  ics as byte[]
				}
			})
			
			def g = grailsApplication.mainContext.getBean('org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib')					
			//Mail para el usuario
			String mailContent = g.render(template:"/seleccionInicial/mailReserva", model:[user: user, tren: tren])
			GParsPool.executeAsyncAndWait ({
				mailService.sendMail {
					to user.email
					subject "Reserva: " + tren.toString() + " " + tren.trayecto + " - www.compartirmesadetren.com"
					html mailContent
				}
			})
		}

		twitter4jService.updateStatus("He reservado el trayecto #" + tren.trayecto + " " + tren.toString() + ". ¿Te interesa? #tarifamesa #alvia compartirmesadetren.com")
    }
}
