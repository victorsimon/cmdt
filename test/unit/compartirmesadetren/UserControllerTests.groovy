package compartirmesadetren

import org.junit.*
import grails.test.mixin.*
import org.codehaus.groovy.grails.plugins.codecs.*
import compartirmesadetren.SHACodec

@TestFor(UserController)
@Mock(User)
class UserControllerTests {

	void testValidAuthenticate() {
		mockCodec(Base64Codec)
		mockCodec(SHACodec)
		
		def user = new User(
			login:"valid",
			password:"valid".encodeAsSHA(),
			role:"user"
			)
		
		mockDomain(User, [user])
		
		controller.params.login = "valid"
		controller.params.password = "valid"
		controller.authenticate()
		assertNotNull controller.session.user
		assertEquals "valid", controller.session.user.login
	}
	
	void testInvalidAuthenticate() {
		mockCodec(Base64Codec)
		mockCodec(SHACodec)
		
		def user = new User(
			login:"valid",
			password:"valid".encodeAsSHA(),
			role:"user"
			)
		
		mockDomain(User, [user])
		
		controller.params.login = "valid"
		controller.params.password = "invalid"
		controller.authenticate()
		assertEquals "/user/login", controller.response.redirectedUrl
	}
}
