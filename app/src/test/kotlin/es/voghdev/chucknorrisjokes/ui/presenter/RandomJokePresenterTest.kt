package es.voghdev.chucknorrisjokes.ui.presenter

import es.voghdev.chucknorrisjokes.app.ResLocator
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RandomJokePresenterTest {
    @Mock lateinit var mockResLocator: ResLocator

    @Mock lateinit var mockNavigator: RandomJokePresenter.Navigator

    @Mock lateinit var mockView: RandomJokePresenter.MVPView

    lateinit var presenter : RandomJokePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = createMockedPresenter()
    }

    private fun createMockedPresenter(): RandomJokePresenter { val presenter = RandomJokePresenter(mockResLocator)
        presenter.view = mockView
        presenter.navigator = mockNavigator
        return presenter
    }
}
