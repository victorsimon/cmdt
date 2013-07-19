package compartirmesadetren

import groovyx.gpars.GParsPool
import grails.util.Environment
import grails.gsp.PageRenderer

class NotificacionesService {

	def mailService
	def twitter4jService
	def calendarioService
	def grailsApplication
	PageRenderer groovyPageRenderer

    def nuevaReserva(tren, user, plazas) {
		def ics = calendarioService.createICal(tren)
		GParsPool.withPool {
			//Mail para la gestion
			GParsPool.executeAsyncAndWait ({
				try {
					mailService.sendMail {
						multipart true
						to grailsApplication.config.grails.mail.contact
						subject "Nueva reserva ${plazas} plaza/s ${tren.toString()} ${tren.trayecto}"
						html "${plazas}|${tren.toString()}|${tren.trayecto}|${user.username}|${user.email}"
						attach "reserva.ics", "text/calendar",  ics as byte[]
					}
				} catch(e) {
					log.error "NotificacionesService nuevaReserva: " + e
				}
			})
			
			//Mail para el usuario
			String mailContent = groovyPageRenderer.render(template:"/seleccionInicial/mailReserva", model:[user: user, tren: tren, plazas: plazas])
			GParsPool.executeAsyncAndWait ({
				try {
					mailService.sendMail {
						to user.email
						subject "Reserva " + tren.toString() + " " + tren.trayecto + " - www.compartirmesadetren.com"
						html mailContent
					}
				} catch(e) {
					log.error "NotificacionesService nuevaReserva: " + e
				}
			})
		}

		twitter4jService.updateStatus("He reservado el trayecto #" + tren.trayecto + " " + tren.toString() + ". ¿Te interesa? #tarifamesa #alvia compartirmesadetren.com")
    }

    def cancelarReserva(peticion) {
		//Mail para la gestion
		def msg = "<a href='https://www.paypal.com/es/cgi-bin/webscr?cmd=_view-a-trans&id=${peticion.paypalTren.payment.paypalTransactionId}'>Paypal ref</a>|${peticion.plazas}|${peticion.user.username}|${peticion.user.email}"
		//Mail para el usuario
		def tren = peticion.paypalTren.tren

		switch (Environment.current) {
		    case Environment.DEVELOPMENT:
		        println "Mensaje a gestion: ${msg}"
		        println "Mensaje a usuario:\n ${peticion.user} ${tren} ${peticion.plazas}"
		        break
		    case Environment.PRODUCTION:
			    try {
					String mailContent = groovyPageRenderer.render template : "/seleccionInicial/mailCancelacion", model : [user: peticion.user, tren: tren, plazas: peticion.plazas]
					mailService.sendMail {
						to grailsApplication.config.grails.mail.contact
						subject "NOTIFICACION| Cancelar la petición ${peticion}"
						html msg
					}
					mailService.sendMail {
						to peticion.user.email
						subject "Cancelaci&oacute;n reserva " + tren.toString() + " " + tren.trayecto + " - www.compartirmesadetren.com"
						html mailContent
					}
				} catch(e) {
					log.error e
				}
		        break
		}
    }

    def anularReserva(peticion) {
		//Mail para la gestion
		def msg = "<a href='https://www.paypal.com/es/cgi-bin/webscr?cmd=_view-a-trans&id=${peticion.paypalTren.payment.paypalTransactionId}'>Paypal ref</a>|${peticion.plazas}|${peticion.user.username}|${peticion.user.email}"
		//Mail para el usuario
		def tren = peticion.paypalTren.tren

		switch (Environment.current) {
		    case Environment.DEVELOPMENT:
		        println "Mensaje a gestion: ${msg}"
		        println "Mensaje a usuario:\n ${peticion.user} ${tren} ${peticion.plazas}"
		        break
		    case Environment.PRODUCTION:
		    	try {
					String mailContent = groovyPageRenderer.render(template:"/seleccionInicial/mailAnulacion", model:[user: peticion.user, tren: tren, plazas: peticion.plazas])
					mailService.sendMail {
						to grailsApplication.config.grails.mail.contact
						subject "NOTIFICACION| Anulada la petición ${peticion}"
						html msg
					}
					mailService.sendMail {
						to peticion.user.email
						subject "Anulaci&oacute;n reserva " + tren.toString() + " " + tren.trayecto + " - www.compartirmesadetren.com"
						html mailContent
					}
				} catch(e) {
					log.error "NotificacionesService nuevaReserva: " + e
				}
		        break
		}
    }

    def reservasSuficientes(peticion) {
		//Mail para la gestion
		def msg = "${peticion.salida}|${peticion.trayecto}"

		switch (Environment.current) {
		    case Environment.DEVELOPMENT:
		        println "Mensaje a gestion: ${msg}"
		        break
		    case Environment.PRODUCTION:
		    	try {
					mailService.sendMail {
						to grailsApplication.config.grails.mail.contact
						subject "NOTIFICACION| Reservas suficientes ${peticion.salida} ${peticion.trayecto}"
						html msg
					}
				} catch(e) {
					log.error "NotificacionesService nuevaReserva: " + e
				}
		        break
		}
    }

    def reservarMesa(peticion) {
		//Mail para la gestion
		def msg = "${peticion.salida}|${peticion.trayecto}"

		switch (Environment.current) {
		    case Environment.DEVELOPMENT:
		        println "Mensaje a gestion: ${msg}"
		        break
		    case Environment.PRODUCTION:
		    	try {
					mailService.sendMail {
						to grailsApplication.config.grails.mail.contact
						subject "NOTIFICACION| Reservar mesa ${peticion.salida} ${peticion.trayecto}"
						html msg
					}
				} catch(e) {
					log.error "NotificacionesService nuevaReserva: " + e
				}
		        break
		}
    }
}
