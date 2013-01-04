package compartirmesadetren

class Peticion {
	
	Date salida
	Trayecto trayecto
	User user
	EstadoPeticion estado

    static constraints = {
		salida(blank: false, nullable: false)
		trayecto(blank: false, nullable: false)
		user(blank: false, nullable: false)
		estado()
    }
	
	String toString() {
		return salida.format("dd-MM-yyyy HH:mm") + " - " + user.username
	}
}
