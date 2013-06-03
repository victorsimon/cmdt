package compartirmesadetren



class ExtraerInfoRenfeJob {
	
	def trenesService
	
    static triggers = {
      simple startDelay: 60000l, repeatInterval: 900000l // execute job once in 15 min
      //simple startDelay: 10000, repeatInterval: 300000l // execute job once in 5 min
    }

    def execute() {
		log.debug  "*********** iniciando extraccion de datos ****************"
		def dia = new Date().clearTime() + 2
		def fechas = []
		60.times {
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
