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
		        def salida = new Date().parse("dd/MM/yyyy HH.mm", "${objetivo.salida.format('dd/MM/yyyy')} ${objetivo.hora}")
		        def tren = Tren.findBySalidaAndTrayecto(salida, objetivo.trayecto)
		     	if (tren && tren.noValido == objetivo.disponible) {
		     		objetivo.disponible = !objetivo.disponible
		     		objetivo.save(flush: true)
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
