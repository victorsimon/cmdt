package compartirmesadetren

class DatosRenfe {

	static searchable = {salida format: 'dd/MM/yyyy'}

	Long timestamp
	Date salida
	Trayecto trayecto
	String rawData

    static constraints = {
    	timestamp()
    	salida()
    	trayecto()
    	rawData()
    }
	
	String toString() {
		"${timestamp} - ${salida.format('dd/MM/yyyy')} ${trayecto.toString()} ${rawData}"
	}

	Boolean estaDisponible(hora) {
        def datos = DatosRenfe.findAllBySalidaAndTrayecto(salida, trayecto, [sort: "timestamp"])                
        def disponible = false
        def ts = 0
        datos.each { dato ->
            if (ts != dato.timestamp) {
                ts = dato.timestamp
                disponible = false
            }
            if (dato.rawData.contains(hora)) {
                disponible = true
            }
        }
       	disponible
	}
}
