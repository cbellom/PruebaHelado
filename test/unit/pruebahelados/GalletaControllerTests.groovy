package pruebahelados



import org.junit.*
import grails.test.mixin.*

@TestFor(GalletaController)
@Mock(Galleta)
class GalletaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/galleta/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.galletaInstanceList.size() == 0
        assert model.galletaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.galletaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.galletaInstance != null
        assert view == '/galleta/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/galleta/show/1'
        assert controller.flash.message != null
        assert Galleta.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/galleta/list'

        populateValidParams(params)
        def galleta = new Galleta(params)

        assert galleta.save() != null

        params.id = galleta.id

        def model = controller.show()

        assert model.galletaInstance == galleta
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/galleta/list'

        populateValidParams(params)
        def galleta = new Galleta(params)

        assert galleta.save() != null

        params.id = galleta.id

        def model = controller.edit()

        assert model.galletaInstance == galleta
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/galleta/list'

        response.reset()

        populateValidParams(params)
        def galleta = new Galleta(params)

        assert galleta.save() != null

        // test invalid parameters in update
        params.id = galleta.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/galleta/edit"
        assert model.galletaInstance != null

        galleta.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/galleta/show/$galleta.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        galleta.clearErrors()

        populateValidParams(params)
        params.id = galleta.id
        params.version = -1
        controller.update()

        assert view == "/galleta/edit"
        assert model.galletaInstance != null
        assert model.galletaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/galleta/list'

        response.reset()

        populateValidParams(params)
        def galleta = new Galleta(params)

        assert galleta.save() != null
        assert Galleta.count() == 1

        params.id = galleta.id

        controller.delete()

        assert Galleta.count() == 0
        assert Galleta.get(galleta.id) == null
        assert response.redirectedUrl == '/galleta/list'
    }
}
