package compartirmesadetren

class Precio {

	Trayecto trayecto
	BigDecimal precioCmdtMin
	BigDecimal precioCmdtMed
	BigDecimal precioCmdtMax
	BigDecimal precioRenfe

    static constraints = {
    	trayecto nullable: false, blank: false
    	precioCmdtMin nullable: false, blank: false
    	precioCmdtMed nullable: false, blank: false
    	precioCmdtMax nullable: false, blank: false
    	precioRenfe nullable: false, blank: false
    }
	
	String toString() {
		return trayecto
	}
}
