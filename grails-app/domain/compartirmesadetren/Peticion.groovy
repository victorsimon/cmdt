package compartirmesadetren

class Peticion {
	
	static searchable = true

	Date salida
	Trayecto trayecto
	User user
	Integer plazas = 1
	EstadoPeticion estado
	PaypalTren paypalTren
	Date dateCreated
	Date lastUpdated		

    static constraints = {
		salida(blank: false, nullable: false, attributes: [precision: "minute"])
		trayecto(blank: false, nullable: false)
		user(blank: false, nullable: false)
		plazas()
		estado()
		paypalTren(nullable: true)
    }
	
	String toString() {
		return salida.format("dd-MM-yyyy HH:mm") + " - " + user.username
	}
}
