package compartirmesadetren

class Asiento {
	static searchable = true
	
	String coche
	String asiento
	static belongsTo = [mesa:Mesa]
	
    static constraints = {
		coche()
		asiento()
		mesa()
    }
	
	String toString() {
		return "Coche ${coche}, asiento ${asiento}"
	}
}
