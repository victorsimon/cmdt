class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/sitemap"(controller: 'sitemap', action : 'sitemap')
		"/robots.txt" (view: "/robots")
		"/"(view: "/index")
		"/mantenimiento"(view:"/mantenimiento")
		"/madrid-pamplona-mesa-renfe"(controller: "seleccionInicial", action: "trayectos")
		"/en-tren-horarios-renfe-contacto"(controller: "contact", action: "index")
		"/billetes-ave-preguntas-frecuentes"(view:"/preguntasfrecuentes")
		"/tarifa-mesa-renfe-como-funciona"(view:"/queycomo")
		"/condiciones-de-uso"(view: "/condiciones-de-uso")
		"500"(view:'/error')
		"404"(view:'/404')
	}
}
