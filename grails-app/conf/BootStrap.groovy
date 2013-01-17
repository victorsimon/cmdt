import compartirmesadetren.Precio
import compartirmesadetren.Estacion
import compartirmesadetren.Role
import compartirmesadetren.Trayecto
import compartirmesadetren.Tren
import compartirmesadetren.User
import compartirmesadetren.UserRole
import grails.util.GrailsUtil;
import java.sql.Time

class BootStrap {

    def init = { servletContext ->
		switch (GrailsUtil.environment) {
			case "development":
				def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
				def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
				def testUser = new User(username: 'me', enabled: true, password: 'password', email: 'vsimon.batanero@gmail.com')
				testUser.save(flush: true)
				UserRole.create testUser, adminRole, true
				UserRole.create testUser, userRole, true
				
				def testUser1 = new User(username: 'you', enabled: true, password: 'password', email: 'vsimon.batanero@gmail.com')
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

				/*
				createTren("00601-ALVIA", madridPamplona)
				createTren("00602-ALVIA", madridPamplona)
				createTren("00603-ALVIA", madridPamplona)
				createTren("00604-ALVIA", madridPamplona)
				
				createTren("00605-ALVIA", pamplonaMadrid)
				createTren("00606-ALVIA", pamplonaMadrid)
				createTren("00607-ALVIA", pamplonaMadrid)
				createTren("00608-ALVIA", pamplonaMadrid)
				*/
				
			break
			case "production":
				if (!Role.findByAuthority('ROLE_ADMIN')) {
					def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
					def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
					def testUser = new User(username: 'me', enabled: true, password: 'password', email: 'vsimon.batanero@gmail.com')
					testUser.save(flush: true)
					UserRole.create testUser, adminRole, true
					UserRole.create testUser, userRole, true
				}
				if (!Estacion.findByNombre("Madrid (*)")) {
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
				}
				break
			case "sandbox":
				if (!Role.findByAuthority('ROLE_ADMIN')) {
					def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
					def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
					def testUser = new User(username: 'me', enabled: true, password: 'password', email: 'vsimon.batanero@gmail.com')
					testUser.save(flush: true)
					UserRole.create testUser, adminRole, true
					UserRole.create testUser, userRole, true
				}
				if (!Estacion.findByNombre("Madrid (*)")) {
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
				}
				break
			default: break
		}
    }
}
