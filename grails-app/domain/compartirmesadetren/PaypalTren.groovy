package compartirmesadetren

import org.grails.paypal.Payment;

class PaypalTren {

	static searchable = true
	
	Payment payment
	Tren tren
	
    static constraints = {
		payment()
		tren()
    }
	
}
