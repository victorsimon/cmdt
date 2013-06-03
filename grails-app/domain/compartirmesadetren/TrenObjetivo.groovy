package compartirmesadetren

class TrenObjetivo {

	static searchable = {salida format: 'dd/MM/yyyy'}

	Date salida
	Trayecto trayecto
	String hora
	Boolean disponible = false
	Boolean activo = true

    static constraints = {
    	salida(blank: false, attributes: [precision: "minute"])
    	trayecto(blank: false)
    	hora(blank: false)
    	disponible()
    	activo()
    }
	
	public setSalida(Date salida) {
		this.salida = salida.clearTime()
	}

	String toString() {
		"${salida.format('dd/MM/yyyy')} ${trayecto?.toString()} ${hora} activo ${activo}"
	}

}
