package compartirmesadetren

class ActionFilters {

    def springSecurityService
    def actionService

    def filters = {
        all(controller:'register', action:'register') {
            after = { Map model ->
                actionService.nuevoRegistro(new User(username: params.username, email: params.email), null)
            }
        }

        all(controller:'register', action:'verifyRegistration') {
            after = { Map model ->
                actionService.registroCompletado(springSecurityService.getCurrentUser())
            }
        }
    }
}
