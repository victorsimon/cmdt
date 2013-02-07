class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/sitemap"(controller: 'sitemap', action : 'sitemap')
		"/robots.txt" (view: "/robots")
		"/"(controller: "seleccionInicial", action: "trayectos")
		"/mantenimiento"(view:"/mantenimiento")
		"/preguntasfrecuentes"(view:"/preguntasfrecuentes")
		"/queycomo"(view:"/queycomo")
		"500"(view:'/error')
		"404"(view:'/404')
	}
}
