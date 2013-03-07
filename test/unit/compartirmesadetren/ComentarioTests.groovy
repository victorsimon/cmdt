package compartirmesadetren



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Comentario)
class ComentarioTest {

    void testConstraints() {
    	//Ningún campo es único, por lo que no necesito iniciar un dato
    	mockForConstraintsTests(Comentario)

    	//la propiedad texto no puede ser null
    	def comentario = new Comentario()
    	assert !comentario.validate()
    	assert comentario.errors.size == 1
    	assert "blank" == comentario.errors["texto"].code
    	println "ping"
    }
}
