package compartirmesadetren

class User {
	static searchable = true

	String login
	String password
	String role = "user"
	
    static constraints = {
		login(blank:false, nullable:false, unique:true)
		password(black:false, nullable:false, password:true)
		role(inList:["admin", "user"])
    }
	
	static transients = ['admin']
	
	boolean isAdmin() {
		return role == "admin"
	}
	
	def beforeInsert = {
		password = password.encodeAsSHA()
	}
	
	String toString() {
		login
	}
}
