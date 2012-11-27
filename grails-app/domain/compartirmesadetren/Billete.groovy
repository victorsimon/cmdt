package compartirmesadetren

class Billete {
	static searchable = true

	static belongsTo = [asiento:Asiento, usuario:User]
	Boolean pagado = Boolean.FALSE
	Date dateCreated
	
    static constraints = {
		asiento()
		usuario()
		pagado()
    }
	
	String toString() {
		return "${asiento} - ${usuario}"
	}
}
