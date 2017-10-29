package es.voghdev.chucknorrisjokes.ui.presenter

import com.nhaarman.mockito_kotlin.verify
import es.voghdev.chucknorrisjokes.app.ResLocator
import org.junit.Before
import org.junit.Test
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

        presenter = RandomJokePresenter(mockResLocator)
    }

    @Test
    fun `should request a random joke on start`() {
        presenter.initialize()
    }

    private fun createMockedPresenter(): RandomJokePresenter {
        presenter.view = mockView
        presenter.navigator = mockNavigator
        return presenter
    }
}
