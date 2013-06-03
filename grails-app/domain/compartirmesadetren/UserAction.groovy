package compartirmesadetren

class UserAction {

	static searchable = true

	String actionKey
	String description
	String tags // Groovy list sintaxis
	String data //  Groovy map sintaxis
	Date dateCreated

    static constraints = {
    	actionKey nullabled: false, blank: false
    }
	
	String toString() {
		return actionKey + " - " + description
	}
}
