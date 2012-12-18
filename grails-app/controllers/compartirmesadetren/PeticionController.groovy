package compartirmesadetren

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class PeticionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	@Secured(['ROLE_ADMIN'])
    def index() {
        redirect(action: "list", params: params)
    }

	@Secured(['ROLE_ADMIN'])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		def list = params.q? Peticion.search(params.q, params).results: Peticion.list(params)
		def total = params.q? Peticion.countHits(params.q): Peticion.count()
        [peticionInstanceList: list, peticionInstanceTotal: total]
    }

	@Secured(['ROLE_ADMIN'])
    def create() {
        [peticionInstance: new Peticion(params)]
    }

	@Secured(['ROLE_ADMIN'])
    def save() {
        def peticionInstance = new Peticion(params)
        if (!peticionInstance.save(flush: true)) {
            render(view: "create", model: [peticionInstance: peticionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'peticion.label', default: 'Peticion'), peticionInstance.id])
        redirect(action: "show", id: peticionInstance.id)
    }

	@Secured(['ROLE_ADMIN'])
    def show(Long id) {
        def peticionInstance = Peticion.get(id)
        if (!peticionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'peticion.label', default: 'Peticion'), id])
            redirect(action: "list")
            return
        }

        [peticionInstance: peticionInstance]
    }

	@Secured(['ROLE_ADMIN'])
    def edit(Long id) {
        def peticionInstance = Peticion.get(id)
        if (!peticionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'peticion.label', default: 'Peticion'), id])
            redirect(action: "list")
            return
        }

        [peticionInstance: peticionInstance]
    }

	@Secured(['ROLE_ADMIN'])
    def update(Long id, Long version) {
        def peticionInstance = Peticion.get(id)
        if (!peticionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'peticion.label', default: 'Peticion'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (peticionInstance.version > version) {
                peticionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'peticion.label', default: 'Peticion')] as Object[],
                          "Another user has updated this Peticion while you were editing")
                render(view: "edit", model: [peticionInstance: peticionInstance])
                return
            }
        }

        peticionInstance.properties = params

        if (!peticionInstance.save(flush: true)) {
            render(view: "edit", model: [peticionInstance: peticionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'peticion.label', default: 'Peticion'), peticionInstance.id])
        redirect(action: "show", id: peticionInstance.id)
    }

	@Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        def peticionInstance = Peticion.get(id)
        if (!peticionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'peticion.label', default: 'Peticion'), id])
            redirect(action: "list")
            return
        }

        try {
            peticionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'peticion.label', default: 'Peticion'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'peticion.label', default: 'Peticion'), id])
            redirect(action: "show", id: id)
        }
    }
}
