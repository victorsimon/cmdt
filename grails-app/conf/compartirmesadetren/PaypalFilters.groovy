package compartirmesadetren

import org.grails.paypal.Payment

class PaypalFilters {

    def filters = {
		
		buyFilter(controller:"paypal", action:"buy") {
			after = {
				new PaypalTren(payment: request.payment, tren: Tren.get(params.itemNumber)).save()
			}
		}
	
		paymentReceivedFilter(controller:'paypal', action:'(success|notifyPaypal)') {
			after = {
				def payment = request.payment
				if(payment && payment.status == org.grails.paypal.Payment.COMPLETE) {
					def paymentRef = PaypalTren.findByPayment(request.payment)
					redirect (controller: "seleccionInicial", action: "peticion", id: paymentRef.tren.id)
				}
			}
		}

    }
}
