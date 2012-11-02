class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: "seleccionInicial", action="trayectos")
		"/mantenimiento"(view:"/mantenimiento")
		"500"(view:'/error')
	}
}
