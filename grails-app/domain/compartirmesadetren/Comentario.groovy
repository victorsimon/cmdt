package compartirmesadetren

class Comentario {

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
