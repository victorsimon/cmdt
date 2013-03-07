package compartirmesadetren

import org.grails.paypal.Payment
import groovyx.gpars.GParsPool

class PaypalFilters {

    def springSecurityService
	def actionService
	def mailService
	def twitter4jService
	def grailsApplication

    def filters = {
		
		buyFilter(controller:"paypal", action:"buy") {
			after = {
				def user = springSecurityService.getCurrentUser()
				new PaypalTren(payment: request.payment, tren: Tren.get(params.itemNumber), user: user).save()
				actionService.inicioReserva(user, params.itemName)
			}
		}
	
		paymentReceivedFilter(controller:'paypal', action:'notifyPaypal') {
			after = {
				def payment = request.payment
				if (!payment && params?.transactionId) { //Puede ser autorizacion
					payment = Payment.findByTransactionId(params.transactionId)
					if (payment) {
						request.payment = payment
						if (params.payment_status == "Pending") {
							payment.status = org.grails.paypal.Payment.PENDING
						} else if (params.payment_status == "Voided") {
							payment.status = org.grails.paypal.Payment.CANCELLED
						} else if (params.payment_status == "Complete") {
							payment.status = org.grails.paypal.Payment.COMPLETE
						}
						payment.save(flush: true)
					}
				} 

				if(params.transaction_entity == "auth" && payment && payment.status == org.grails.paypal.Payment.PENDING) {
					def paymentRef = PaypalTren.findByPayment(request.payment)
					Tren tren = Tren.read(paymentRef.tren.id)
					def user = User.read(paymentRef.user.id)
					Peticion peticion = new Peticion(salida: tren.salida, 
													user: user, 
													trayecto: tren.trayecto, 
													estado: EstadoPeticion.A_LA_ESPERA,
													paypalTren: paymentRef)
					peticion.save(flush: true)
					
					GParsPool.withPool {
						//Mail para la gestion
						GParsPool.executeAsyncAndWait ({
							mailService.sendMail {
								to grailsApplication.config.grails.mail.contact
								subject "Nueva reserva " + tren.toString() + " " + tren.trayecto
								html tren.toString() + "|" + tren.trayecto + "|" + user.username + "|" + user.email
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

					try {
						twitter4jService.updateStatus("He reservado el trayecto #" + tren.trayecto + " " + tren.toString() + ". ¿Te interesa? #tarifamesa #alvia compartirmesadetren.com")
					} catch(Exception e) {
						log.error e
					}

					actionService.reservaCompletada(user, peticion)
				}
			}
		}

		paymentReceivedFilter(controller:'paypal', action:'success') {
			after = {
				def payment = request.payment
				if(payment && payment.status == org.grails.paypal.Payment.COMPLETE) {
					def paymentRef = PaypalTren.findByPayment(request.payment)
					redirect (controller: "seleccionInicial", action: "peticion", id: paymentRef.tren.id)
				}
			}
		}

		paymentReceivedFilter(controller:'paypal', action:'cancel') {
			after = {
				def payment = request.payment
				if(payment && payment.status == org.grails.paypal.Payment.CANCELLED) {
					redirect (controller: "seleccionInicial")
				}
			}
		}

    }
}
