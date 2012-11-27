package compartirmesadetren

import org.cyberneko.html.parsers.SAXParser;

class TrenesService {
	
    def List<Tren> buscarTrenes(List<Date> salidas, Trayecto trayecto) {
		def List<Tren> trenesDisponibles = []
		def cookieSession = extraerCookieSession()
		salidas.each { salida ->
			def URLconsulta = componerURL(trayecto, salida)
			def URLConnection conexion = conectarConCookieSession(URLconsulta, cookieSession)
			def contenidoLimpio = limpiarContenido(conexion.content.text)
			if (haCambiado(salida, contenidoLimpio)) {
				//conexion = conectarConCookieSession(URLconsulta, cookieSession)
				//def Map<String, String> trenesProgramados = extraerTrenes(conexion)
				def Map<String, String> trenesProgramados = extraerTrenes(contenidoLimpio)
				trenesProgramados.each {
					//println it.value
					def trenProgramado = it.value.split ("\\|")
					def horaSalida = setHora(salida, trenProgramado[7].split("\\.")[0].toInteger(),
						trenProgramado[7].split("\\.")[1].toInteger())
					def horaLlegada = setHora(salida, trenProgramado[8].split("\\.")[0].toInteger(),
						trenProgramado[8].split("\\.")[1].toInteger())
					if (horaLlegada < horaSalida) //Ajustamos el dia si llega al día siguiente (la programación no da la info necesaria)
						horaLlegada = horaLlegada + 1
					
					Tren tren = Tren.findBySalida(horaSalida.time)
					if (!tren) {
						tren = new Tren(nombre: trenProgramado[4] + '-' + trenProgramado[1],
							salida: horaSalida.time,
							llegada: horaLlegada.time,
							trayecto: trayecto);
						tren.save(flush: true)
					}
					if (tren.hasErrors())
						println tren.errors
					else
						trenesDisponibles << tren
					//println tren.toString() + " " + tren.id + " con salida " + tren.salida.time
				}
				borrarTrenes(salida, trayecto, trenesDisponibles)
				guardarMD5(salida, contenidoLimpio)
			} else {
				trenesDisponibles.addAll(Tren.buscarPorDiaSalida(salida.time))
			}		
		}
		return trenesDisponibles
    }

	private String limpiarContenido(String content) {
		//content = content.replaceAll(/<!DOCTYPE.+>/, "<!DOCTYPE html>")
		content = content.replaceAll(/id\s=\s"longHoy"\s+value="\d+"/, "COCODRILO")
		content = content.replaceAll(/"Clase.Ljava.lang.String;@.+">/, '"COCODRILO">')
		return content
	}
	
	private boolean haCambiado(Date fecha, String content) {
		def nuevoMD5 = content.encodeAsMD5()
		def key = fecha.format('dd-MM-yyyy')
		//println nuevoMD5
		def contentMD5 = ContentMD5.findByKey(key)
		if (contentMD5) {
			println "Hay para esa fecha"
			if (nuevoMD5 == contentMD5.md5HexString) {
				println "Es la misma"
				return false
			} else {
				contentMD5.delete(flush: true)
			}
		}
		println "Ha cambiado"
		return true
	}

	private guardarMD5(Date fecha, String content) {
		def nuevoMD5 = content.encodeAsMD5()
		def key = fecha.format('dd-MM-yyyy')
		def nuevoContentMD5 = new ContentMD5(key: key, md5HexString: nuevoMD5)
		nuevoContentMD5.save()
	}

	private borrarTrenes(Date salida, Trayecto trayecto, List<Tren> trenesAMantener) {
		def trenesBorrar = Tren.buscarPorDiaSalida(salida.time)
		trenesBorrar.each {
			if (!trenesAMantener.contains(it)) {
				//println it.toString() + " " + it.id + " con salida " + it.salida.time + " borrado"	
				it.delete(flush: true) 
			} //else
				//println it.toString() + " " + it.id + " con salida " + it.salida.time + " mantenido"
		}
	}
	
	private setHora(Date fechaOriginal, Integer hora, Integer minuto) {
		def fechaYHora = new GregorianCalendar()
		fechaYHora.time = fechaOriginal
		fechaYHora.set(Calendar.HOUR_OF_DAY, hora)
		fechaYHora.set(Calendar.MINUTE, minuto)
		fechaYHora.set(Calendar.SECOND, minuto)
		fechaYHora.set(Calendar.MILLISECOND, 0)
		//println "Time establecido a " + fechaYHora.time.time	
		return fechaYHora
	}

	private Map<String, String> extraerTrenes(URLConnection busqueda) {
		def parser = new SAXParser()
		parser.setFeature("http://xml.org/sax/features/namespaces", false);
		def slurper = new XmlSlurper(parser)
		def htmlParser = slurper.parse(busqueda.content)

		def Map<String, String> trenes = [:]
		htmlParser.'**'.findAll { it.@id.toString() ==~ /trenClaseOcupacion[1-9]00/ }.each {
			trenes.put(it.@id.toString(), it.@value.toString() + '|' + it.parent().text().trim().replaceAll(",", "."))
		}
		return trenes
	}

	private Map<String, String> extraerTrenes(String busqueda) {
		println "Parsing text"
		def parser = new SAXParser()
		parser.setFeature("http://xml.org/sax/features/namespaces", false);
		def slurper = new XmlSlurper(parser)
		def htmlParser = slurper.parseText(busqueda)

		def Map<String, String> trenes = [:]
		htmlParser.'**'.findAll { it.@id.toString() ==~ /trenClaseOcupacion[1-9]00/ }.each {
			trenes.put(it.@id.toString(), it.@value.toString() + '|' + it.parent().text().trim().replaceAll(",", "."))
		}
		return trenes
	}
	
	private conectarConCookieSession(String URL, List cookie) {
		def busqueda = URL.toURL().openConnection()
		if (cookie)
			busqueda.setRequestProperty('Cookie', cookie.toString())
		return busqueda
	}

	private List extraerCookieSession() {
		def inicio = "https://venta.renfe.com/vol/index.do".toURL().openConnection()
		def cookie
		inicio.getHeaderFields().each {
			if (it.getKey() == 'Set-Cookie')
				cookie = it.getValue()
		}
		return cookie
	}

	private String componerURL(Trayecto trayecto, Date salida) {
		def nombreOrigen = URLEncoder.encode(trayecto.origen.nombre, "UTF-8")
		def nombreDestino = URLEncoder.encode(trayecto.destino.nombre, "UTF-8")
		def dia = salida.format('dd')
		def mes = salida.format('MM')
		def anio = salida.format('yyyy')
		def URL = "https://venta.renfe.com/vol/buscarTren.do?" +
				"url_logout=&tipoUsuario=N&targetUsuario=&idioma=&pais=&" +
				"MODULO=&desOrigen=${nombreOrigen}&desDestino=${nombreDestino}&ninos=0&" +
				"currenLocation=menuBusqueda&operation=&grupos=false&" +
				"tipoOperacion=IND&empresas=false&iv=i&" +
				"IdOrigen=${trayecto.origen.code}&FechaIdaSel=${dia}%2F${mes}%2F${anio}&HoraIdaSel=00%3A00&" +
				"IdDestino=${trayecto.destino.code}&adultos=1&desOrigen=&desDestino=&ninos=0&txtoUsuario=&" +
				"txtoPass=&pagina%2F=&msgRedirigirALogin=Se+ha+producido+un+error+al+cargar+el+Journal"
		return URL
	}
}
