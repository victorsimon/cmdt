package compartirmesadetren

class NotificacionPaypal {

	String parametros
	Date dateCreated

    static constraints = {
    	dateCreated()
    	parametros(maxSize: 2048)
    }
	
	String toString() {
		"${dateCreated} - ${parametros}"
	}
}
