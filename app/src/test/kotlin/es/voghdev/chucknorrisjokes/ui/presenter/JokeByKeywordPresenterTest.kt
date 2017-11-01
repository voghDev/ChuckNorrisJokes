package es.voghdev.chucknorrisjokes.ui.presenter

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.model.Joke
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class JokeByKeywordPresenterTest() {
    @Mock lateinit var mockResLocator: ResLocator

    @Mock lateinit var mockNavigator: JokeByKeywordPresenter.Navigator

    @Mock lateinit var mockView: JokeByKeywordPresenter.MVPView

    @Mock lateinit var mockChuckNorrisRepository: ChuckNorrisRepository

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

    @Test
    fun `should show an empty case when an empty list is returned by the API`() {
        givenTheApiReturnsNoResults()

        runBlocking {
            presenter.initialize()
        }

        presenter.onSearchButtonClicked("this query returns no results")

        verify(mockView).showEmptyCase()
    }

    private fun givenTheApiReturnsNoResults() {
        whenever(mockChuckNorrisRepository.getRandomJokeByKeyword(anyString())).thenReturn(Pair(emptyList<Joke>(), null))
    }

    private fun createMockedPresenter(): JokeByKeywordPresenter {
        val presenter = JokeByKeywordPresenter(mockResLocator, mockChuckNorrisRepository)
        presenter.view = mockView
        presenter.navigator = mockNavigator
        return presenter
    }
}
