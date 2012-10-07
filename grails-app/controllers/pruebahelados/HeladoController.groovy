package pruebahelados

import org.springframework.dao.DataIntegrityViolationException

class HeladoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [heladoInstanceList: Helado.list(params), heladoInstanceTotal: Helado.count()]
    }

    def create() {
        [heladoInstance: new Helado(params)]
    }

    def save() {
        def heladoInstance = new Helado(params)
        if (!heladoInstance.save(flush: true)) {
            render(view: "create", model: [heladoInstance: heladoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'helado.label', default: 'Helado'), heladoInstance.id])
        redirect(action: "show", id: heladoInstance.id)
    }

    def show(Long id) {
        def heladoInstance = Helado.get(id)
        if (!heladoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'helado.label', default: 'Helado'), id])
            redirect(action: "list")
            return
        }

        [heladoInstance: heladoInstance]
    }

    def edit(Long id) {
        def heladoInstance = Helado.get(id)
        if (!heladoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'helado.label', default: 'Helado'), id])
            redirect(action: "list")
            return
        }

        [heladoInstance: heladoInstance]
    }

    def update(Long id, Long version) {
        def heladoInstance = Helado.get(id)
        if (!heladoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'helado.label', default: 'Helado'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (heladoInstance.version > version) {
                heladoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'helado.label', default: 'Helado')] as Object[],
                          "Another user has updated this Helado while you were editing")
                render(view: "edit", model: [heladoInstance: heladoInstance])
                return
            }
        }

        heladoInstance.properties = params

        if (!heladoInstance.save(flush: true)) {
            render(view: "edit", model: [heladoInstance: heladoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'helado.label', default: 'Helado'), heladoInstance.id])
        redirect(action: "show", id: heladoInstance.id)
    }

    def delete(Long id) {
        def heladoInstance = Helado.get(id)
        if (!heladoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'helado.label', default: 'Helado'), id])
            redirect(action: "list")
            return
        }

        try {
            heladoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'helado.label', default: 'Helado'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'helado.label', default: 'Helado'), id])
            redirect(action: "show", id: id)
        }
    }
}
