package compartirmesadetren

class ActionService {

    def nuevoComentario(User user) {
    	def actionKey = "comentario"
    	def description = "Nuevo comentario"
    	def tags = ["contacto"]
    	def data = user?[username: user.username]: []

    	UserAction ua = new UserAction(actionKey: actionKey, description: description, tags: tags.toString(), data: data.toString())
    	ua.save()
    }

    def nuevoContactar(User user) {
    	def actionKey = "contactar"
    	def description = "Comunicacion por mail"
    	def tags = ["mail", "contacto"]
    	def data = user?[username: user.username]: []

    	UserAction ua = new UserAction(actionKey: actionKey, description: description, tags: tags.toString(), data: data.toString())
    	ua.save()
    }

    def inicioReserva(User user, String tren) {
    	def actionKey = "inicio-reserva"
    	def description = "Inicio de reservar"
    	def tags = ["usuario", "nuevo", "reserva", "tren"]
    	def data = [username: user.username, tren: tren]

    	UserAction ua = new UserAction(actionKey: actionKey, description: description, tags: tags.toString(), data: data.toString())
    	ua.save()
    }

    def reservaCompletada(User user, Peticion peticion) {
    	def actionKey = "reserva-completada"
    	def description = "Reservar completada"
    	def tags = ["usuario", "completado", "reserva", "mail", "tren"]
    	def data = [username: user.username, trayecto: peticion.trayecto, salida: peticion.salida]

    	UserAction ua = new UserAction(actionKey: actionKey, description: description, tags: tags.toString(), data: data.toString())
    	ua.save()
    }

    def nuevoRegistro(User user, String social) {
    	def actionKey = "registro"
    	def description = "Nuevo registro de usuario"
    	def tags = ["usuario", "nuevo", "registro"]
    	if (social)
    		tags << "social"
    	def data = [username: user.username, email: user.email, social: ""]
    	if (social)
    		data.social = social

    	UserAction ua = new UserAction(actionKey: actionKey, description: description, tags: tags.toString(), data: data.toString())
    	ua.save()
    }

    def registroCompletado(User user) {
    	def actionKey = "registro-completado"
    	def description = "Registro completado"
    	def tags = ["usuario", "completado", "registro"]
    	def data = [username: user.email]

    	UserAction ua = new UserAction(actionKey: actionKey, description: description, tags: tags.toString(), data: data.toString())
    	ua.save()
    }

    def consultaTrenes(User user, Trayecto trayecto, Date fecha, boolean ofertas) {
    	if (!ofertas) {
    		def fecha_limite = new Date() + 3
    		fecha_limite = fecha_limite.clearTime()
    		if (fecha < fecha_limite) {
    			return
    		}
    	}
    	def actionKey = "consulta-trenes"
    	def description = "Consulta de trenes"
    	def tags = ["trenes", "pregunta", "dia", "trayecto", "tren"]
    	def usuario = user? user.username: ""
    	def data = [trayecto: trayecto, salida: fecha?.format("dd/MM/yyyy HH:mm"), usuario: usuario, ofertas: ofertas]

    	UserAction ua = new UserAction(actionKey: actionKey, description: description, tags: tags.toString(), data: data.toString())
    	ua.save()
    }

    def seleccionTren(User user, String tren) {
    	def actionKey = "seleccion-tren"
    	def description = "Previo a reserva tren"
    	def tags = ["usuario", "seleccion", "tren"]
    	def data = [username: user.username, tren: tren]

    	UserAction ua = new UserAction(actionKey: actionKey, description: description, tags: tags.toString(), data: data.toString())
    	ua.save()
    }

}
