package compartirmesadetren

class Trayecto {
	static searchable = true

	static belongsTo = [
		origen:Estacion,
		destino:Estacion
		]
	BigDecimal precioMesa = 0.0
	static hasMany = [trenes:Tren]
	
    static constraints = {
		origen()
		destino()
    	precioMesa(scale:2, min:0.0, max:200.0)
	}
	
	String toString() {
		return "${origen} - ${destino}"
	}
}
