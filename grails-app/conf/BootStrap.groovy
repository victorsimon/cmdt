import compartirmesadetren.Asiento
import compartirmesadetren.Estacion
import compartirmesadetren.Mesa
import compartirmesadetren.Role
import compartirmesadetren.Trayecto
import compartirmesadetren.Tren
import compartirmesadetren.User
import compartirmesadetren.UserRole
import compartirmesadetren.Usuario
import grails.util.GrailsUtil;
import java.sql.Time

class BootStrap {

    def init = { servletContext ->
		switch (GrailsUtil.environment) {
			case "development":
				def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
				def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
				def testUser = new User(username: 'me', enabled: true, password: 'password')
				testUser.save(flush: true)
				UserRole.create testUser, adminRole, true
				UserRole.create testUser, userRole, true
				
				def testUser1 = new User(username: 'you', enabled: true, password: 'password')
				testUser1.save(flush: true)
				UserRole.create testUser1, userRole, true

				def madrid = new Estacion(nombre:"Madrid (*)", code:"MADRI")
				madrid.save()

				def pamplona = new Estacion(nombre:"Pamplona", code:"80100")
				pamplona.save()
				
				def madridPamplona = new Trayecto(
					origen:madrid,
					destino:pamplona,
					precioMesa:94.2
					)
				madridPamplona.save()
				
				def pamplonaMadrid = new Trayecto(
					origen:pamplona,
					destino:madrid,
					precioMesa:94.2
					)
				pamplonaMadrid.save()

				createTren("00601-ALVIA", madridPamplona)
				createTren("00602-ALVIA", madridPamplona)
				createTren("00603-ALVIA", madridPamplona)
				createTren("00604-ALVIA", madridPamplona)
				
				createTren("00605-ALVIA", pamplonaMadrid)
				createTren("00606-ALVIA", pamplonaMadrid)
				createTren("00607-ALVIA", pamplonaMadrid)
				createTren("00608-ALVIA", pamplonaMadrid)

				def victor = new Usuario(
					nombre:"Victor",
					apellidos:"Simon Batanero",
					email:"vsimon.batanero@gmail.com",
					movil:"629570963",
					user: testUser
					)
				victor.save()
				println victor.errors
				
				def ixa = new Usuario(
					nombre:"Isabel",
					apellidos:"Berruezo Adulate",
					email:"isaberruezo@gmail.com",
					movil:"629570963",
					user: testUser1
					)
				ixa.save()
				println ixa.errors

				
			break
			default: break
		}
    }

	private createTren(String nombre, Trayecto madridPamplona) {
		def alvia = new Tren(
				nombre:nombre,
				salida:new Date(),
				llegada:new Date(),
				trayecto:madridPamplona
				)
		alvia.save()
		println alvia.errors

		def mesa1 = new Mesa(tren:alvia)
		mesa1.save()
		println mesa1.errors

		def asiento11 = new Asiento(
				coche:"3",
				asiento:"7a",
				mesa:mesa1
				)
		asiento11.save()
		println asiento11.errors

		def asiento12 = new Asiento(
				coche:"3",
				asiento:"7b",
				mesa:mesa1
				)
		asiento12.save()
		println asiento12.errors

		def asiento13 = new Asiento(
				coche:"3",
				asiento:"8a",
				mesa:mesa1
				)
		asiento13.save()
		println asiento13.errors

		def asiento14 = new Asiento(
				coche:"3",
				asiento:"8a",
				mesa:mesa1
				)
		asiento14.save()
		println asiento14.errors

		def mesa2 = new Mesa(tren:alvia)
		mesa2.save()
		println mesa2.errors

		def asiento21 = new Asiento(
				coche:"3",
				asiento:"7c",
				mesa:mesa2
				)
		asiento21.save()
		println asiento21.errors

		def asiento22 = new Asiento(
				coche:"3",
				asiento:"7d",
				mesa:mesa2
				)
		asiento22.save()
		println asiento22.errors

		def asiento23 = new Asiento(
				coche:"3",
				asiento:"8c",
				mesa:mesa2
				)
		asiento23.save()
		println asiento23.errors

		def asiento24 = new Asiento(
				coche:"3",
				asiento:"8d",
				mesa:mesa2
				)
		asiento24.save()
		println asiento24.errors
	}
	
    def destroy = {
    }
	
}
