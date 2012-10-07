package pruebahelados

import org.springframework.dao.DataIntegrityViolationException

class VendedorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [vendedorInstanceList: Vendedor.list(params), vendedorInstanceTotal: Vendedor.count()]
    }

    def create() {
        [vendedorInstance: new Vendedor(params)]
    }

    def save() {
        def vendedorInstance = new Vendedor(params)
        if (!vendedorInstance.save(flush: true)) {
            render(view: "create", model: [vendedorInstance: vendedorInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'vendedor.label', default: 'Vendedor'), vendedorInstance.id])
        redirect(action: "show", id: vendedorInstance.id)
    }

    def show(Long id) {
        def vendedorInstance = Vendedor.get(id)
        if (!vendedorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vendedor.label', default: 'Vendedor'), id])
            redirect(action: "list")
            return
        }

        [vendedorInstance: vendedorInstance]
    }

    def edit(Long id) {
        def vendedorInstance = Vendedor.get(id)
        if (!vendedorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vendedor.label', default: 'Vendedor'), id])
            redirect(action: "list")
            return
        }

        [vendedorInstance: vendedorInstance]
    }

    def update(Long id, Long version) {
        def vendedorInstance = Vendedor.get(id)
        if (!vendedorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vendedor.label', default: 'Vendedor'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (vendedorInstance.version > version) {
                vendedorInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'vendedor.label', default: 'Vendedor')] as Object[],
                          "Another user has updated this Vendedor while you were editing")
                render(view: "edit", model: [vendedorInstance: vendedorInstance])
                return
            }
        }

        vendedorInstance.properties = params

        if (!vendedorInstance.save(flush: true)) {
            render(view: "edit", model: [vendedorInstance: vendedorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'vendedor.label', default: 'Vendedor'), vendedorInstance.id])
        redirect(action: "show", id: vendedorInstance.id)
    }

    def delete(Long id) {
        def vendedorInstance = Vendedor.get(id)
        if (!vendedorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'vendedor.label', default: 'Vendedor'), id])
            redirect(action: "list")
            return
        }

        try {
            vendedorInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'vendedor.label', default: 'Vendedor'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'vendedor.label', default: 'Vendedor'), id])
            redirect(action: "show", id: id)
        }
    }
}
