package compartirmesadetren

class Estacion {
	static searchable = true

	String nombre
	String code //Codigo de renfe ejem: madrid - MADRI, pamplona - 80100
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
