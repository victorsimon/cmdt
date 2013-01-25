package compartirmesadetren

class FAQ {

	String pregunta
	String respuesta

    static constraints = {
    	pregunta(nullable: false, blank: false)
    	respuesta(size: 1..2048)
    }
	
	String toString() {
		pregunta
	}
}
