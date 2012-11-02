package compartirmesadetren

class Estacion {
	static searchable = true

	String nombre
	static hasMany = [trayectos:Trayecto]
	static mappedBy = [trayectos:'origen']

    static constraints = {
		nombre(blank:false)
    }
	
	static mapping = {
		sort "nombre"
	}
	
	String toString(){
		return "${nombre}"
	}
}
