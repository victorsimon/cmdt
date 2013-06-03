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
}
