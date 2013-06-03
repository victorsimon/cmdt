package compartirmesadetren

import ch.silviowangler.groovy.util.builder.ICalendarBuilder

class CalendarioService {

    def createICal(Tren tren) {
    	def start = tren.salida
    	def end = tren.llegada
    	def summary = tren.trayecto.toString()
    	def description = "Nueva reserva " + tren.toString() + " " + tren.trayecto.toString()
    	def name = "SITO"
    	def email = "compartirmesadetren@gmail.com"

    	def builder = new ICalendarBuilder()
    	def e = {
			calendar {
				events {
	      			event(start: start, 
	               		end: end,
	               		description: description, 
	               		summary: summary) {
	        			organizer(name: name, email: email)
	        		}
	      		}
	      	}
    	}
        builder.invokeMethod('translate', e)
    	builder.toString()
    }
}
