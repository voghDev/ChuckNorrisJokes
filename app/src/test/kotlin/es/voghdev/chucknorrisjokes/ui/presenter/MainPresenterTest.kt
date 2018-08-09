package es.voghdev.chucknorrisjokes.ui.presenter

import es.voghdev.chucknorrisjokes.app.ResLocator
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainPresenterTest() {
    @Mock
    lateinit var mockResLocator: ResLocator

    @Mock
    lateinit var mockNavigator: MainPresenter.Navigator

    @Mock
    lateinit var mockView: MainPresenter.MVPView

    lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = createMockedPresenter()
    }

    private fun createMockedPresenter(): MainPresenter {
        val presenter = MainPresenter(mockResLocator)
        presenter.view = mockView
        presenter.navigator = mockNavigator
        return presenter
    }
}
