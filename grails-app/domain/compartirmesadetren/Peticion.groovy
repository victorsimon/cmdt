package compartirmesadetren

class Peticion {
	
	Date salida
	Trayecto trayecto
	User user

    static constraints = {
		salida(blank: false, nullable: false)
		trayecto(blank: false, nullable: false)
		user(blank: false, nullable: false)
    }
	
	String toString() {
		return salida.format("dd-MM-yyyy HH:mm") + " - " + user.username
	}
}
