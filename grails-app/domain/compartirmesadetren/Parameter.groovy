package compartirmesadetren

class Parameter {

	static searchable = true
	
	String name
	String type
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
