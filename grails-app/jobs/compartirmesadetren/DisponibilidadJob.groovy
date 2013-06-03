package compartirmesadetren



class DisponibilidadJob {

	def mailService
	def grailsApplication

    static triggers = {
      simple repeatInterval: 60000l // execute job once in 60 seconds
    }

    def execute() {
    	TrenObjetivo.findAllByActivo(true).each { objetivo ->
    		if (objetivo.salida <= new Date().clearTime()) {
    			objetivo.activo = false
    		} else {
		        def results = DatosRenfe.findAllBySalidaAndTrayecto(objetivo.salida, 
		        	objetivo.trayecto, [sort: "timestamp"])
		        def disponible = objetivo.disponible
		        def ts = 0 
		        results.each { dato ->
		        	if (ts != dato.timestamp) {
		        		ts = dato.timestamp
		        		disponible = false
		        	}
		        	if (dato.rawData.contains(objetivo.hora)) {
		        		disponible = true
		        	}
		     	}
		     	if (disponible != objetivo.disponible) {
		     		objetivo.disponible = disponible
		     		def texto = """
Cambio de estado: 
${objetivo.salida.format('dd/MM/yyyy')} ${objetivo.hora} ${objetivo.trayecto.toString()}
Nuevo estado ${objetivo.disponible? 'DISPONIBLE': 'NO DISPONIBLE'}
"""
					try {
						mailService.sendMail {
							to grailsApplication.config.grails.mail.contact
							subject 'CAMBIO DE ESTADO EN TREN OBJETIVO'
							html texto
						}
					}
					catch(Exception e) {
						log.error "Cambio de estado en tren objetivo sin posibilidad de ser avisado. ${e}"
					}
		     	}
	    	}
    	}
    }
}
