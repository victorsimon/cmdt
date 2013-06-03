package compartirmesadetren

class Comentario {

	static searchable = true
	
	String texto
	User user
	
	static constraints = {
		texto blank: false, nullable: false
		user nullable: true
	}
	
	String toString() {
		return id + texto
	}
}
