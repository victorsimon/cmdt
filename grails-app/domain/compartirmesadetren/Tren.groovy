package compartirmesadetren

class Tren {
	static searchable = true

	String nombre
	Date salida 
	Date llegada
	Boolean noValido = false
	Boolean bloqueado = false
	static belongsTo = [trayecto:Trayecto]
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		nombre(blank:false)
		salida(attributes: [precision: "minute",],)
		llegada(attributes: [precision: "minute",],)
		noValido()
		bloqueado()
		trayecto()
    }
	
	static mapping = {
		sort "salida"
	}
	
	String toString() {
		return "${nombre}, ${salida.format('dd-MM-yyy HH:mm')}"
	}
	
	static List<Tren> buscarPorDiaSalida(Long time, Trayecto trayecto) {
		def inicioDia = new GregorianCalendar()
		inicioDia.time = new Date(time)
		inicioDia.set(Calendar.HOUR_OF_DAY, 0)
		inicioDia.set(Calendar.MINUTE, 0)
		inicioDia.set(Calendar.MILLISECOND, 0)
		def finDia = inicioDia.time + 1
		return Tren.findAllBySalidaBetweenAndTrayecto(inicioDia.time, finDia, trayecto)
	}
}
