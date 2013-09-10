package compartirmesadetren

import org.cyberneko.html.parsers.SAXParser
//import java.util.concurrent.*

class TrenesService {
	
    def List<Tren> buscarTrenes(List<Date> salidas, Trayecto trayecto) {
		def List<Tren> trenesDisponibles = []
		salidas.each {salida ->
			trenesDisponibles.addAll(Tren.buscarPorDiaSalida(salida.time, trayecto))
		}
		return trenesDisponibles
    }
	
	def extraerTrenesDisponiblesPorDia(Trayecto trayecto, List<Date> salidas) {
		def cookieSession = extraerCookieSession()
		salidas.each {salida ->
			def List<Tren> trenesDisponiblesDia = []
			def URLconsulta = componerURL(trayecto, salida)
			def URLConnection conexion = conectarConCookieSession(URLconsulta, cookieSession)
			try {
				def contenidoLimpio = limpiarContenido(conexion.content.text)
				def Map<String, String> trenesProgramados = extraerTrenes(contenidoLimpio)
				if (haCambiado(salida, trayecto, trenesProgramados)) {
					Long timestamp = new Date().time
					List<Tren> trenes = []

					//Restringimos el numero de trenes
					/*
					log.debug "Restringiendo por horas"
					def diaDeLaSemana = salida.getAt(Calendar.DAY_OF_WEEK) //SUNDAY = 1
					def primero
					if ([2,3,4].contains(diaDeLaSemana)) {
						primero = 0
					} else {
						primero = 1
					}
					def totalTrenesDia = trenesProgramados.size()
					log.debug "Es el día " + diaDeLaSemana + " y el primero admitido es el " + primero + " de " + totalTrenesDia
					*/

					trenesProgramados.eachWithIndex() { t, i ->
						DatosRenfe rawData = new DatosRenfe(timestamp: timestamp, salida: salida, 
							trayecto: trayecto, rawData: t.value).save(flush: true)
						//if (i == primero || i == totalTrenesDia - 1) {
							log.debug "Añadimos " + salida
							Tren tmp = convertirLineasEnTrenes(t.value, salida, trayecto)
							if (tmp) trenes.add(tmp)
						/*
						} else {
							log.debug "Descartamos " + salida
						}
						*/
					}
					guardarReferenciaDeCambio(salida, trayecto, trenesProgramados)
					marcarTrenesObsoletos(salida, trayecto, trenes)
				}
			} catch (e) {
				log.warn "EL SITE DE RENFE ESTA CAIDO"
			}
		}
	}

	def consultarTrenesDisponibles(Trayecto trayecto, Date salida) {
		def cookieSession = extraerCookieSession()
		def List<Tren> trenesDisponiblesDia = []
		def URLconsulta = componerURL(trayecto, salida)
		def URLConnection conexion = conectarConCookieSession(URLconsulta, cookieSession)
		def contenidoLimpio = limpiarContenido(conexion.content.text)
		def Map<String, String> trenesProgramados = extraerTrenes(contenidoLimpio)
		return trenesProgramados
	}

	private Tren convertirLineasEnTrenes(String source, Date salida, Trayecto trayecto) {
		def trenProgramado = source.split ("\\|")
		def horaSalida = setHora(salida, trenProgramado[7].split("\\.")[0].toInteger(),
				trenProgramado[7].split("\\.")[1].toInteger())
		def horaLlegada = setHora(salida, trenProgramado[8].split("\\.")[0].toInteger(),
				trenProgramado[8].split("\\.")[1].toInteger())
		if (horaLlegada < horaSalida) //Ajustamos el dia si llega al d�a siguiente (la programaci�n no da la info necesaria)
			horaLlegada = horaLlegada + 1

		log.debug "Convirtiendo " + source
	
		Tren tren = Tren.findBySalidaAndTrayecto(horaSalida.time, trayecto)
		//Tren tren = Tren.buscarPorDiaSalida(horaSalida.time.time, trayecto)
		if (!tren) {
			tren = crearTren (trenProgramado[4] + "-" + trenProgramado[1], horaSalida.time, horaLlegada.time, trayecto)
		} else {
			tren.noValido = false
		}
		return tren
	}

	private Tren crearTren(String nombre, Date salida, Date llegada, Trayecto trayecto){
		Tren tren = new Tren(nombre: nombre,
			salida: salida,
			llegada: llegada,
			noValido: false,
			trayecto: trayecto);
		tren.save(flush: true)
		if (tren.hasErrors())
			log.error(tren.errors)
		return tren
	}
	
	private String limpiarContenido(String content) {
		//content = content.replaceAll(/<!DOCTYPE.+>/, "<!DOCTYPE html>")
		content = content.replaceAll(/id\s=\s"longHoy"\s+value="\d+"/, "COCODRILO")
		content = content.replaceAll(/"Clase.Ljava.lang.String;@.+">/, '"COCODRILO">')
		return content
	}
	
	private boolean haCambiado(Date fecha, Trayecto trayecto, Map<String, String> trenes) {
		def content = trenes.toMapString()
		ContentMD5.withTransaction { status ->
			def contentKey = fecha.format('dd-MM-yyyy') + ' - ' + trayecto
			log.debug(contentKey)
			def contentMD5 = ContentMD5.findByContentKey(contentKey)
			if (contentMD5) {
				log.debug("Hay para esa fecha")
				def nuevoMD5 = content.encodeAsMD5()
				if (nuevoMD5 == contentMD5.md5HexString) {
					log.debug("Es la misma")
					return false
				} else {
					contentMD5.delete(flush: true)
				}
			}
			log.debug("Ha cambiado")
			return true
		}
	}

	private boolean tengoDatosRecientes(Date fecha, Trayecto trayecto) {
		ContentMD5.withTransaction { status ->
			def contentKey = fecha.format('dd-MM-yyyy') + ' - ' + trayecto
			log.debug(contentKey)
			def contentMD5 = ContentMD5.findByContentKey(contentKey)
			if (contentMD5) {
				log.debug("Hay para esa fecha")
				def currentTime = Calendar.instance
				currentTime.add(Calendar.MINUTE, 30)
				if (contentMD5.timeStamp < currentTime.time.time) {
					log.debug("Es reciente")
					return true
				}
			}
			log.debug("Caducado")
			return false
		}
	}

	private guardarReferenciaDeCambio(Date fecha, Trayecto trayecto, Map<String, String> trenes) {
		def content = trenes.toMapString()
		def nuevoMD5 = content.encodeAsMD5()
		def contentKey = fecha.format('dd-MM-yyyy') + ' - ' + trayecto
		def nuevoContentMD5 = new ContentMD5(contentKey: contentKey, md5HexString: nuevoMD5, timeStamp: new Date().time)
		nuevoContentMD5.save()
	}

	private marcarTrenesObsoletos(Date salida, Trayecto trayecto, List<Tren> trenesAMantener) {
		if (Tren.count() > 0) {
			log.debug "Marcando trenes obsoletos"
			def trenesBorrar = Tren.buscarPorDiaSalida(salida.time, trayecto)
			trenesBorrar.each { Tren tren ->
				log.debug tren
				if (!trenesAMantener.contains(tren)) {
					log.debug "MARCANDO COMO NO VALIDO"
					tren.noValido = true
				} else {
					tren.noValido = false
				}
				tren.save(flush: true)
			}
		}
	}
	
	private setHora(Date fechaOriginal, Integer hora, Integer minuto) {
		def fechaYHora = new GregorianCalendar()
		fechaYHora.time = fechaOriginal
		fechaYHora.set(Calendar.HOUR_OF_DAY, hora)
		fechaYHora.set(Calendar.MINUTE, minuto)
		fechaYHora.set(Calendar.SECOND, 0)
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
		htmlParser.'**'.findAll { it.@id.toString() ==~ /trenClaseOcupacion[0-9]00/ }.each {
			trenes.put(it.@id.toString(), it.@value.toString() + '|' + it.parent().text().trim().replaceAll(",", "."))
		}
		return trenes
	}

	private Map<String, String> extraerTrenes(String busqueda) {
		log.debug("Parsing text")
		def parser = new SAXParser()
		parser.setFeature("http://xml.org/sax/features/namespaces", false);
		def slurper = new XmlSlurper(parser)
		def htmlParser = slurper.parseText(busqueda)

		def Map<String, String> trenes = [:]
		htmlParser.'**'.findAll { it.@id.toString() ==~ /trenClaseOcupacion[0-9]00/ }.each {
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
