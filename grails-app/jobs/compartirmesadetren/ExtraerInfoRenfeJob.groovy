package compartirmesadetren



class ExtraerInfoRenfeJob {
	
	def trenesService
	
    static triggers = {
      simple startDelay: 10000, repeatInterval: 900000l // execute job once in 15 min
    }

    def execute() {
		log.debug  "*********** iniciando extraccion de datos ****************"
		def dia = new Date() + 2
		def fechas = []
		45.times {
			fechas << dia + it
		}
		def trayectos = Trayecto.list()
		trayectos.each { trayecto ->
			log.debug "*********** trayecto " + trayecto + " ****************"
			trenesService.extraerTrenesDisponiblesPorDia(trayecto, fechas)
		}    
		log.debug "*********** extraccion de datos finalizada ****************"
    }
}
