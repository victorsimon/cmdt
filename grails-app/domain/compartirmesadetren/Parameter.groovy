package compartirmesadetren

class Parameter {

	String name
	String value
	static belongsTo = Report
	static hasMany = [reports: Report]
	Date dateCreated
	Date lastUpdated

    static constraints = {
    }
	
	String toString() {
		name
	}
}
