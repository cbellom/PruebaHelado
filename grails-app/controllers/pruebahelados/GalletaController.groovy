package pruebahelados

import org.springframework.dao.DataIntegrityViolationException

class GalletaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [galletaInstanceList: Galleta.list(params), galletaInstanceTotal: Galleta.count()]
    }

    def create() {
        [galletaInstance: new Galleta(params)]
    }

    def save() {
        def galletaInstance = new Galleta(params)
        if (!galletaInstance.save(flush: true)) {
            render(view: "create", model: [galletaInstance: galletaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'galleta.label', default: 'Galleta'), galletaInstance.id])
        redirect(action: "show", id: galletaInstance.id)
    }

    def show(Long id) {
        def galletaInstance = Galleta.get(id)
        if (!galletaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'galleta.label', default: 'Galleta'), id])
            redirect(action: "list")
            return
        }

        [galletaInstance: galletaInstance]
    }

    def edit(Long id) {
        def galletaInstance = Galleta.get(id)
        if (!galletaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'galleta.label', default: 'Galleta'), id])
            redirect(action: "list")
            return
        }

        [galletaInstance: galletaInstance]
    }

    def update(Long id, Long version) {
        def galletaInstance = Galleta.get(id)
        if (!galletaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'galleta.label', default: 'Galleta'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (galletaInstance.version > version) {
                galletaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'galleta.label', default: 'Galleta')] as Object[],
                          "Another user has updated this Galleta while you were editing")
                render(view: "edit", model: [galletaInstance: galletaInstance])
                return
            }
        }

        galletaInstance.properties = params

        if (!galletaInstance.save(flush: true)) {
            render(view: "edit", model: [galletaInstance: galletaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'galleta.label', default: 'Galleta'), galletaInstance.id])
        redirect(action: "show", id: galletaInstance.id)
    }

    def delete(Long id) {
        def galletaInstance = Galleta.get(id)
        if (!galletaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'galleta.label', default: 'Galleta'), id])
            redirect(action: "list")
            return
        }

        try {
            galletaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'galleta.label', default: 'Galleta'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'galleta.label', default: 'Galleta'), id])
            redirect(action: "show", id: id)
        }
    }
}
