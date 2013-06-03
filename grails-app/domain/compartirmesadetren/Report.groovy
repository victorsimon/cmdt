package compartirmesadetren

class Report {

	static searchable = true

	String jasper
	String format = "PDF,HTML,XML,CSV"
	String name
	String description
	Date dateCreated
	Date lastUpdated
	static hasMany = [parameters: Parameter]

    static constraints = {
    	name blank: false, unique: true
    	description blank: false
    	jasper blank: false
    	format()
    }
	
	String toString() {
		name
	}
}
