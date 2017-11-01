package es.voghdev.chucknorrisjokes.ui.presenter

import com.nhaarman.mockito_kotlin.verify
import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class JokeByKeywordPresenterTest() {
    @Mock lateinit var mockResLocator: ResLocator

    @Mock lateinit var mockNavigator: JokeByKeywordPresenter.Navigator

    @Mock lateinit var mockView: JokeByKeywordPresenter.MVPView

    @Mock lateinit var mockChuckNorrisRepository : ChuckNorrisRepository

    lateinit var presenter: JokeByKeywordPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = createMockedPresenter()
    }

    @Test
    fun `should not accept an empty text as search keyword`() {
        runBlocking {
            presenter.initialize()
        }

        presenter.onSearchButtonClicked("")

        verify(mockView).showKeywordError("You must enter a keyword")
    }

    @Test
    fun `should request a random joke by keyword when "search" button is clicked with a valid keyword`() {
        runBlocking {
            presenter.initialize()
        }

        presenter.onSearchButtonClicked("lee")

        verify(mockChuckNorrisRepository).getRandomJokeByKeyword("lee")
    }

    private fun createMockedPresenter(): JokeByKeywordPresenter {
        val presenter = JokeByKeywordPresenter(mockResLocator, mockChuckNorrisRepository)
        presenter.view = mockView
        presenter.navigator = mockNavigator
        return presenter
    }
}
