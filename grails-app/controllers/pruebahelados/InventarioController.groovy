package pruebahelados

import org.springframework.dao.DataIntegrityViolationException

class InventarioController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [inventarioInstanceList: Inventario.list(params), inventarioInstanceTotal: Inventario.count()]
    }

    def create() {
        [inventarioInstance: new Inventario(params)]
    }

    def save() {
        def inventarioInstance = new Inventario(params)
        if (!inventarioInstance.save(flush: true)) {
            render(view: "create", model: [inventarioInstance: inventarioInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'inventario.label', default: 'Inventario'), inventarioInstance.id])
        redirect(action: "show", id: inventarioInstance.id)
    }

    def show(Long id) {
        def inventarioInstance = Inventario.get(id)
        if (!inventarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inventario.label', default: 'Inventario'), id])
            redirect(action: "list")
            return
        }

        [inventarioInstance: inventarioInstance]
    }

    def edit(Long id) {
        def inventarioInstance = Inventario.get(id)
        if (!inventarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inventario.label', default: 'Inventario'), id])
            redirect(action: "list")
            return
        }

        [inventarioInstance: inventarioInstance]
    }

    def update(Long id, Long version) {
        def inventarioInstance = Inventario.get(id)
        if (!inventarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inventario.label', default: 'Inventario'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (inventarioInstance.version > version) {
                inventarioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'inventario.label', default: 'Inventario')] as Object[],
                          "Another user has updated this Inventario while you were editing")
                render(view: "edit", model: [inventarioInstance: inventarioInstance])
                return
            }
        }

        inventarioInstance.properties = params

        if (!inventarioInstance.save(flush: true)) {
            render(view: "edit", model: [inventarioInstance: inventarioInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'inventario.label', default: 'Inventario'), inventarioInstance.id])
        redirect(action: "show", id: inventarioInstance.id)
    }

    def delete(Long id) {
        def inventarioInstance = Inventario.get(id)
        if (!inventarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inventario.label', default: 'Inventario'), id])
            redirect(action: "list")
            return
        }

        try {
            inventarioInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'inventario.label', default: 'Inventario'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'inventario.label', default: 'Inventario'), id])
            redirect(action: "show", id: id)
        }
    }
}
