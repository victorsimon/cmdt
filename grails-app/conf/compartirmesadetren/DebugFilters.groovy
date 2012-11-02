package compartirmesadetren

class DebugFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
				print "DEBUG: ${actionUri} called"
				if (session?.user?.login) {
					println "DEBUG: by ${session.user.login} and role ${session.user.role}"
				}
				println "DEBUG: ${params}"
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
