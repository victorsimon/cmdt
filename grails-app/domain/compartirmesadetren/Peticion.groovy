package compartirmesadetren

class Peticion {
	
	Date salida
	User user

    static constraints = {
		salida(blank: false)
		user(blank: false)
    }
	
	String toString() {
		return salida.format("dd-MM-yyyy HH:mm") + " - " + user.username
	}
}
