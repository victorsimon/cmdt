package compartirmesadetren

class SeleccionInicialController {
	
	def trayectos(Integer opcion) {
		def Date fecha = params.fecha
		def Trayecto trayecto
		List trenes
		if (params.trayecto) {
			trayecto = Trayecto.findById(params.trayecto)
			trenes = Tren.findAllByTrayecto(trayecto)
			if (!opcion) {
				List t = []
				trenes.each {
					if (it.salida.format("dd-MM-yyy") == fecha.format("dd-MM-yyy")) t << it	}
				trenes = t
			} else if (opcion == 1) { //proximos tres dias
				List t = []
				trenes.each {
					if ( it.salida > new Date() - 1 &&
						it.salida <= new Date() + 3) t << it	}
				trenes = t
			}
		}
		[trayectos: Trayecto.list(), trayecto: trayecto, fecha: fecha, trenes: trenes]
	}

	def proximos = {
		chain (action: "trayectos", params: [trayecto: params.trayecto, opcion: 1])
	}
}
