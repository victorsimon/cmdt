package compartirmesadetren

class DebugFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
				log.debug("${actionUri} called")
				if (session?.user?.login) {
					log.debug("by ${session.user.login} and role ${session.user.role}")
				}
				log.debug("${params}")
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
