package pruebahelados



import org.junit.*
import grails.test.mixin.*

@TestFor(InventarioController)
@Mock(Inventario)
class InventarioControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/inventario/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.inventarioInstanceList.size() == 0
        assert model.inventarioInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.inventarioInstance != null
    }

    void testSave() {
        controller.save()

        assert model.inventarioInstance != null
        assert view == '/inventario/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/inventario/show/1'
        assert controller.flash.message != null
        assert Inventario.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/inventario/list'

        populateValidParams(params)
        def inventario = new Inventario(params)

        assert inventario.save() != null

        params.id = inventario.id

        def model = controller.show()

        assert model.inventarioInstance == inventario
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/inventario/list'

        populateValidParams(params)
        def inventario = new Inventario(params)

        assert inventario.save() != null

        params.id = inventario.id

        def model = controller.edit()

        assert model.inventarioInstance == inventario
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/inventario/list'

        response.reset()

        populateValidParams(params)
        def inventario = new Inventario(params)

        assert inventario.save() != null

        // test invalid parameters in update
        params.id = inventario.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/inventario/edit"
        assert model.inventarioInstance != null

        inventario.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/inventario/show/$inventario.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        inventario.clearErrors()

        populateValidParams(params)
        params.id = inventario.id
        params.version = -1
        controller.update()

        assert view == "/inventario/edit"
        assert model.inventarioInstance != null
        assert model.inventarioInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/inventario/list'

        response.reset()

        populateValidParams(params)
        def inventario = new Inventario(params)

        assert inventario.save() != null
        assert Inventario.count() == 1

        params.id = inventario.id

        controller.delete()

        assert Inventario.count() == 0
        assert Inventario.get(inventario.id) == null
        assert response.redirectedUrl == '/inventario/list'
    }
}
