import compartirmesadetren.Precio
import compartirmesadetren.Estacion
import compartirmesadetren.Role
import compartirmesadetren.Trayecto
import compartirmesadetren.Tren
import compartirmesadetren.User
import compartirmesadetren.UserRole
import compartirmesadetren.FAQ
import compartirmesadetren.Report
import compartirmesadetren.Parameter
import compartirmesadetren.Peticion
import compartirmesadetren.EstadoPeticion
import compartirmesadetren.PaypalTren
import org.grails.paypal.Payment
import grails.util.GrailsUtil
import java.sql.Time

class BootStrap {

    def init = { servletContext ->
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+2"))
    	//Aqui inicializo los datos comunes. Mas abajo pongo los especificos de cada entorno
    	def adminRole = Role.findByAuthority('ROLE_ADMIN')
    	def userRole = Role.findByAuthority('ROLE_USER')
		if (!adminRole) {
			adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
			userRole = new Role(authority: 'ROLE_USER').save(flush: true)
		}
		def madridPamplona
		def pamplonaMadrid
		if (!Estacion.findByNombre("Madrid (*)")) {
			def madrid = new Estacion(nombre:"Madrid (*)", code:"MADRI")
			madrid.save()

			def pamplona = new Estacion(nombre:"Pamplona", code:"80100")
			pamplona.save()
			
			madridPamplona = new Trayecto(
				origen:madrid,
				destino:pamplona,
				precioMesa:94.2
				)
			madridPamplona.save()
			
			pamplonaMadrid = new Trayecto(
				origen:pamplona,
				destino:madrid,
				precioMesa:94.2
				)
			pamplonaMadrid.save()
			def precioMadridPamplona = new Precio(
				trayecto:madridPamplona,
				precioCmdtMin:29.99,
				precioCmdtMed:34.99,
				precioCmdtMax:37.99,
				precioRenfe:58.90
				)
			precioMadridPamplona.save()

			def precioPamplonaMadrid = new Precio(
				trayecto:pamplonaMadrid,
				precioCmdtMin:29.99,
				precioCmdtMed:34.99,
				precioCmdtMax:37.99,
				precioRenfe:58.90
				)
			precioPamplonaMadrid.save()
		}
    	if (!FAQ.findByPreguntaLike('%incluye el precio?')) {
			def faq1 = new FAQ(
				pregunta: '¿Qué incluye el precio?', 
				respuesta: """
Nosotros nos ocupamos de conseguirte el mejor precio. Por ello, incluye:<ul>
<li>Gestión de asignación de los pasajeros a las mesas y la comunicación en Redes Sociales y demás medios para poder completarlas. Recuerda que CONSEGUIRTE EL MEJOR PRECIO POSIBLE es nuestro objetivo.</li>
<li>La compra de billetes y la <b>venta por individual</b> a cada uno de los miembros de la mesa así como el cobro del billete de forma totalmente segura gracias a PayPal. SIN COMPLICACIONES.</li></ul>
				"""
				)		
			faq1.save()		

			def faq2 = new FAQ(
				pregunta: '¿Cobráis gastos de gestión sobre los 3 precios marcados al inicio?', 
				respuesta: """
No, los 3 precios que aparecen son cerrados e incluyen todos los gastos expuestos en la primera pregunta.					
				"""
				)				
			faq2.save()		

			def faq3 = new FAQ(
				pregunta: '¿El sistema me cobra el billete en el momento que lo reservo?', 
				respuesta: """
Al introducir tus datos de tarjeta en Paypal, tan sólo estás confirmando que estás interesado en el billete y que tu tarjeta es real. Es una manera de verificar los datos, como realizan webs tan conocidas como www.booking.com.<br/>
Insistimos que el cobro del billete se realizará SÓLO cuando dispongamos de tu billete, y siempre se informará del precio que finalmente deberás pagar, que como máximo será el mayor de los 3 únicos precios que ofertamos en cada trayecto.<br/>
<br/>
CON CMDT SALDRÁS GANANDO PORQUE <b>SIEMPRE</b> SERÁ MÁS ECÓNOMICO QUE LA TARIFA NORMAL DEL BILLETE DE RENFE.
				"""
				)				
			faq3.save()		

			def faq4 = new FAQ(
				pregunta: '¿Puedo hacer una reserva sin tarjeta de crédito?', 
				respuesta: """
<b>No se puede realizar una reserva sin tarjeta de crédito</b>. Al realizar la reserva se acepta que el precio máximo a pagar puede ser el indicado sobre las tres opciones.<br/>
Cuando introduces tu número de tarjeta, se comprueba la validez de la tarjeta y la disponibilidad de fondos, que en este último caso, será sobre el precio máximo a pagar. <b>PayPal es el método más seguro de pago</b> y no realizará ningún otro tipo de comprobación en tu cuenta.<br/>
<br/>
Sin embargo, recuerda que SITO en CMDT, siempre intentará conseguir el mejor precio. <b>A más viajeros, menos pagas.</b>
				"""
				)				
			faq4.save()		

			def faq5 = new FAQ(
				pregunta: '¿Cómo sé si mi reserva está confirmada? Y ¿cuándo pago?', 
				respuesta: """
La reserva queda confirmada cuando se completa la mesa, y el cobro SÓLO se realizará cuando dispongamos del billete, el cual te enviaremos al momento.
					"""
				)				
			faq5.save()		

			def faq6 = new FAQ(
				pregunta: '¿Puedo cancelar o modificar mi reserva?', 
				respuesta: """
En cualquier momento puedes ponerte en contacto con nosotros para comunicarnos cualquier incidencia sobre tu reserva. <br/>
Podrás acceder a tu cuenta, en la que podrás ver tu lista de reservas pendientes. Al clickar sobre una en concreto, podrás enviarnos un e-mail con tus comentarios. <br>
Nosotros intentaremos realizar los cambios siempre que no influyan en otros viajeros, y te atenderemos y responderemos a todas las cuestiones planteadas.
				"""
				)				
			faq6.save()		

			def faq7 = new FAQ(
				pregunta: '¿Puedo hacer una reserva/compra 24 hrs antes de la salida?', 
				respuesta: """
No. Las reservas y la gestión del billete se realiza como mínimo 48 hrs. antes de la salida, para poder ofrecerte el mejor servicio y conseguirte el mejor precio.
"""
				).save()

		}

		if (!FAQ.findByPreguntaLike('Se supone que me debería llegar un email, pero no lo veo...')) {
		 new FAQ(
			pregunta: 'Se supone que me debería llegar un email, pero no lo veo...', 
			respuesta: """
Algunos de nuestros procesos implican notificaciones por email. <br/>
Si esperas una notificación nuestra y no te llega, en algunos casos es porque tu cliente de correo lo guarda como spam. Comprueba que no esté como spam en tu correo. <br/>
Si encuentras que tu cliente de correo a guardado la notificación como spam, te aconsejamos que selecciones el email y establezcas en tus opciones que no es spam.
"""
			).save()
		}
		if (!FAQ.findByPreguntaLike('¿En qué casos recibiré un email vuestro?')) {
		 new FAQ(
			pregunta: '¿En qué casos recibiré un email vuestro?', 
			respuesta: """
Recibiras notificaciones de SITO relacionadas con la gestión y el funcionamiento del sistema: 
<ul>
	<li>Cuando crees un nuevo usuario no vinculado a ninguna red social, se te enviará un email para confirmar la dirección de email que nos has dado.</li>
	<li>Si olvidas tu clave, te enviaremos un email a tu correo con un link desde el que podras cambiarla.</li>
	<li>Al realizar la reserva para un viaje, te enviaremos un email de confirmación de la misma.</li>
	<li>También te enviaremos un email con tu billete para que puedas viajar</li>
	<li>Si hay alguna comunicación que realizar durante nuestro proceso de gestión de la reserva, lo haremos vía email; o en casos de urgencia, y si disponemos de el, te llamaremos a tu movil.</li>
</ul>
"""
			).save()
		}
		if (!Parameter.findByName("ID")) {
			new Parameter(
					name: "ID",
					type: "number"
				).save(flush: true)
		}
		if (!Report.findByName("Usuarios registrados")) {
			def p = Parameter.findByName("ID")
			new Report(
					name: "Usuarios registrados",
					description: "Listado de usuarios dados de alta",
					jasper: "usuarios_registrados"
				).addToParameters(p).save()
		}
		if (!Report.findByName("Comentarios")) {
			new Report(
					name: "Comentarion",
					description: "Comentarios de los usuarios",
					jasper: "comentarios"
				).save()
		}
		switch (GrailsUtil.environment) {
			case "development":
				def testUser = new User(username: 'admin', enabled: true, password: 'password', email: 'vsimon.batanero@gmail.com')
				testUser.save(flush: true)
				UserRole.create testUser, adminRole, true
				UserRole.create testUser, userRole, true
				
				def testUser1 = new User(username: 'user', enabled: true, password: 'password', email: 'vsimon.batanero@gmail.com')
				testUser1.save(flush: true)
				UserRole.create testUser1, userRole, true
				def date = new Date()
				date.clearTime()
				date.putAt(Calendar.HOUR_OF_DAY, 19)
				date.putAt(Calendar.MINUTE, 35)
				def tren1 = new Tren(
					nombre: "ALVIA-00001",
					salida: date,
					llegada: date,
					trayecto: madridPamplona,
					noValido: false
					)
				tren1.save(flush: true, failOnError: true)
				def tren2 = new Tren(
					nombre: "ALVIA-00002",
					salida: date,
					llegada: date,
					trayecto: pamplonaMadrid,
					noValido: false
					)
				tren2.save(flush: true, failOnError: true)
				def payment = new Payment(paypalTransactionId: "123456789", buyerId: 0)
				payment.save(flush: true)
				def paypalTren = new PaypalTren(payment: payment, user: testUser, tren: tren1)
				paypalTren.save(flush: true, failOnError: true)
				def salida = tren1.salida - 2
				10.times() {
					paypalTren.tren = tren1
					paypalTren.save(flush: true)
					def peticion = new Peticion(
						salida: salida++,
						trayecto: tren1.trayecto,
						user: testUser,
						plazas: 1,
						estado: EstadoPeticion.A_LA_ESPERA,
						paypalTren: paypalTren
						)
					peticion.save(flush: true)
					def peticion2 = new Peticion(
						salida: salida,
						trayecto: tren1.trayecto,
						user: testUser,
						plazas: 2,
						estado: EstadoPeticion.A_LA_ESPERA,
						paypalTren: paypalTren
						)
					peticion2.save(flush: true)
				}
				salida = tren1.salida - 3
				10.times() {
					paypalTren.tren = tren2
					paypalTren.save(flush: true)
					def peticion = new Peticion(
						salida: salida++,
						trayecto: tren2.trayecto,
						user: testUser,
						plazas: 1,
						estado: EstadoPeticion.A_LA_ESPERA,
						paypalTren: paypalTren
						)
					peticion.save(flush: true)
				}
			break
			case "production":
				if(!User.findByUsername('admin')) {
					def testUser = new User(username: 'admin', enabled: true, password: 'X12dpi', email: 'vsimon.batanero@gmail.com')
					testUser.save(flush: true)
					UserRole.create testUser, adminRole, true
					UserRole.create testUser, userRole, true
				}
			break
			case "sandbox":
				if(!User.findByUsername('admin')) {
					def testUser = new User(username: 'admin', enabled: true, password: 'X12dpi', email: 'vsimon.batanero@gmail.com')
					testUser.save(flush: true)
					UserRole.create testUser, adminRole, true
					UserRole.create testUser, userRole, true
				}
			break
			default: break
		}
    }
}
