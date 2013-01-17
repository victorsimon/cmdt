package compartirmesadetren

class ComentarioController {
	
	def enviar (Comentario command) {
		println "1"
		if (!command.validate()) {
		println "2"
			return
		}
		
		println "3"
		command.save()
		
		println "4"
		flash.message = "Tu comentario ha sido enviado correctamente" 
	}
	
}