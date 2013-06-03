package compartirmesadetren

import org.grails.paypal.Payment

class PaypalFilters {

    def springSecurityService
	def actionService
	def mailService
	def twitter4jService
	def grailsApplication
	def notificacionesService

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
				def notificacion = new NotificacionPaypal(parametros: params?.toMapString()).save(flush: true)
				if (!payment && params?.transactionId && params.transactionId) { 
					payment = Payment.findByTransactionId(params.transactionId)
					if (payment) {
						request.payment = payment
						switch(params.payment_status) {
							case "Pending":
								payment.status = org.grails.paypal.Payment.PENDING
							break
							case "Voided":
								payment.status = org.grails.paypal.Payment.CANCELLED
							break
							case "Complete":
								payment.status = org.grails.paypal.Payment.COMPLETE
							break
							case "Expired":
								payment.status = org.grails.paypal.Payment.COMPLETE
							break
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
					
					actionService.reservaCompletada(user, peticion)

					try {
						notificacionesService.nuevaReserva(tren, user)
					} catch(e) {
						println e
					}

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
