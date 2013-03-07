package compartirmesadetren

import org.grails.paypal.Payment;

class PaypalTren {

	static searchable = true
	
	Payment payment
	Tren tren
	User user
	Date dateCreated
	Date lastUpdated	
	
    static constraints = {
		payment()
		tren()
		user()
    }
	
}
