package compartirmesadetren

class Usuario {
	static searchable = true

	String nombre
	String apellidos
	String email
	String movil
	static hasMany = [billetes:Billete]
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		nombre(blank:false)
		apellidos(blank:false)
		email(blank:false, email:true)
		movil()
		billetes()
    }
	
	String toString() {
		return "${nombre} ${apellidos}"
	}
}
