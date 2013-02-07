package compartirmesadetren

class Report {

	String jasper
	String format = "PDF,HTML,XML,CSV"
	String name
	String desc
	Date dateCreated
	Date lastUpdated
	static hasMany = [parameters: Parameter]

    static constraints = {
    	name blank: false
    	desc blank: false
    	jasper blank: false
    	format()
    }
	
	String toString() {
		name
	}
}
