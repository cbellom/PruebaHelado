package pruebahelados



import org.junit.*
import grails.test.mixin.*

@TestFor(HeladoController)
@Mock(Helado)
class HeladoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/helado/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.heladoInstanceList.size() == 0
        assert model.heladoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.heladoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.heladoInstance != null
        assert view == '/helado/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/helado/show/1'
        assert controller.flash.message != null
        assert Helado.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/helado/list'

        populateValidParams(params)
        def helado = new Helado(params)

        assert helado.save() != null

        params.id = helado.id

        def model = controller.show()

        assert model.heladoInstance == helado
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/helado/list'

        populateValidParams(params)
        def helado = new Helado(params)

        assert helado.save() != null

        params.id = helado.id

        def model = controller.edit()

        assert model.heladoInstance == helado
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/helado/list'

        response.reset()

        populateValidParams(params)
        def helado = new Helado(params)

        assert helado.save() != null

        // test invalid parameters in update
        params.id = helado.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/helado/edit"
        assert model.heladoInstance != null

        helado.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/helado/show/$helado.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        helado.clearErrors()

        populateValidParams(params)
        params.id = helado.id
        params.version = -1
        controller.update()

        assert view == "/helado/edit"
        assert model.heladoInstance != null
        assert model.heladoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/helado/list'

        response.reset()

        populateValidParams(params)
        def helado = new Helado(params)

        assert helado.save() != null
        assert Helado.count() == 1

        params.id = helado.id

        controller.delete()

        assert Helado.count() == 0
        assert Helado.get(helado.id) == null
        assert response.redirectedUrl == '/helado/list'
    }
}
