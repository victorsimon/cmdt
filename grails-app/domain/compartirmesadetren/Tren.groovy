package compartirmesadetren

class Tren {
	static searchable = true

	String nombre
	Date salida 
	Date llegada
	static belongsTo = [trayecto:Trayecto]
	static hasMany = [mesas:Mesa]
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		nombre(blank:false)
		salida(validator: {return (it > new Date() - 1)})
		llegada(validator: {return (it > new Date() - 1)})
		trayecto()
		mesas()
    }
	
	static mapping = {
		sort "salida"
	}
	
	String toString() {
		return "${nombre}, ${salida.format('dd-MM-yyy hh:mm')}"
	}
	
}
