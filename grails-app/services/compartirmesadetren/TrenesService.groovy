package compartirmesadetren

import org.cyberneko.html.parsers.SAXParser;

class TrenesService {

    def buscarTren(Date salida, Trayecto trayecto) {
		/*
		def parser = new SAXParser()
		def slurper = new XmlSlurper(parser)
		def URL = "C:/Users/CompartirTren/Downloads/buscarTren/Renfe - Compra de Billetes.htm"
		File busqueda = new File(URL)
		def htmlParser = slurper.parse(busqueda)
		*/

		def busqueda = busquedaEnRenfe(trayecto, salida)
		Map trenes = extraerTrenes(busqueda)

		trenes.each {
			println it.value
		}		
		println trenes
    }

	private Map extraerTrenes(URLConnection busqueda) {
		def parser = new SAXParser()
		parser.setFeature("http://xml.org/sax/features/namespaces", false);
		def slurper = new XmlSlurper(parser)
		def htmlParser = slurper.parse(busqueda.content)

		def Map trenes = [:]
		htmlParser.'**'.findAll { it.@id.toString() ==~ /trenClaseOcupacion[1-9]00/ }.each {
			trenes.put(it.@id.toString(), it.@value.toString() + '|' + it.parent().text().trim())
		}
		return trenes
	}

	private URLConnection busquedaEnRenfe(Trayecto trayecto, Date salida) {
		def inicio = "https://venta.renfe.com/vol/index.do".toURL().openConnection()
		def cookie
		inicio.getHeaderFields().each {
			if (it.getKey() == 'Set-Cookie')
				cookie = it.getValue()
		}

		def nombreOrigen = URLEncoder.encode(trayecto.origen.nombre, "UTF-8")
		def nombreDestino = URLEncoder.encode(trayecto.destino.nombre, "UTF-8")
		def dia = salida.format('dd')
		def mes = salida.format('MM')
		def anio = salida.format('yyyy')
		def URL = "https://venta.renfe.com/vol/buscarTren.do?" +
				"url_logout=&tipoUsuario=N&targetUsuario=&idioma=&pais=&" +
				"MODULO=&desOrigen=" + nombreOrigen + "&desDestino=" + nombreDestino + "&ninos=0&" +
				"currenLocation=menuBusqueda&operation=&grupos=false&" +
				"tipoOperacion=IND&empresas=false&iv=i&" +
				"IdOrigen=" + trayecto.origen.code + "&FechaIdaSel=" + dia + "%2F" + mes + "%2F" + anio + "&HoraIdaSel=00%3A00&" +
				"IdDestino=" + trayecto.destino.code + "&adultos=1&desOrigen=&desDestino=&ninos=0&txtoUsuario=&" +
				"txtoPass=&pagina%2F=&msgRedirigirALogin=Se+ha+producido+un+error+al+cargar+el+Journal"
		println URL
		def busqueda = URL.toURL().openConnection()
		busqueda.setRequestProperty('Cookie', cookie.toString())
		return busqueda
	}
}
