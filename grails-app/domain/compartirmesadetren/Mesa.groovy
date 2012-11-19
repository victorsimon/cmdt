package compartirmesadetren

class Mesa {
	static searchable = true

	static belongsTo = [tren:Tren]
	static hasMany = [asientos:Asiento]
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		tren()
		asientos()
    }
	
	String toString(){
		return "${tren} - ${id}"
	}
}
