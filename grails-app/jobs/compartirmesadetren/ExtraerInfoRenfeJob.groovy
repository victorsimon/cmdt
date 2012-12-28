package compartirmesadetren



class ExtraerInfoRenfeJob {
	
	def trenesService
	
    static triggers = {
      simple startDelay: 10000, repeatInterval: 300000l // execute job once in 5 seconds
    }

    def execute() {
		println "*********** iniciando extraccion de datos ****************"
		def dia = new Date() + 2
		def fechas = []
		30.times {
			fechas << dia + it
		}
		def trayectos = Trayecto.list()
		trayectos.each { trayecto ->
			println "*********** trayecto " + trayecto + " ****************"
			trenesService.extraerTrenesDisponiblesPorDia(trayecto, fechas)
		}    
		println "*********** extraccion de datos finalizada ****************"
    }
}
