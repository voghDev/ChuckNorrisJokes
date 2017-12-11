package es.voghdev.chucknorrisjokes.ui.presenter

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.model.Joke
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

class JokeByKeywordPresenterTest() {
    @Mock lateinit var mockResLocator: ResLocator

    @Mock lateinit var mockNavigator: JokeByKeywordPresenter.Navigator

    @Mock lateinit var mockView: JokeByKeywordPresenter.MVPView

    @Mock lateinit var mockChuckNorrisRepository: ChuckNorrisRepository

    lateinit var presenter: JokeByKeywordPresenter

    val exampleJoke = Joke(
            id = "GdEH64AkS9qEQCmqMwM2Rg",
            iconUrl = "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
            url = "http://api.chucknorris.io/jokes/GdEH64AkS9qEQCmqMwM2Rg",
            value = "Chuck Norris knows how to say souffle in the French language."
    )

    val anotherJoke = Joke(id = "abc",
            iconUrl = "http://chuck.image.url",
            url = "http://example.url",
            value = "We have our fears, fear has its Chuck Norris'es")

    val someJokes = listOf(
            exampleJoke,
            anotherJoke
    )

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = createMockedPresenter()
    }

    @Test
    fun `should not accept an empty text as search keyword`() {
        runBlocking {
            presenter.initialize()

            presenter.onSearchButtonClicked("")
        }

        verify(mockView).showKeywordError("You must enter a keyword")
    }

    @Test
    fun `should request a random joke by keyword when "search" button is clicked with a valid keyword`() {
        givenTheApiReturnsNoResults()

        runBlocking {
            presenter.initialize()

            presenter.onSearchButtonClicked("lee")
        }

        verify(mockChuckNorrisRepository).getRandomJokeByKeyword("lee")
    }

    @Test
    fun `should show an empty case when an empty list is returned by the API`() {
        givenTheApiReturnsNoResults()

        runBlocking {
            presenter.initialize()

            presenter.onSearchButtonClicked("this query returns no results")
        }

        verify(mockView).showEmptyCase()
    }

    @Test
    fun `should add the first joke to the list when a list of two jokes is returned by the API`() {
        givenTheApiReturns(someJokes)

        runBlocking {
            presenter.initialize()

            presenter.onSearchButtonClicked("chan")
        }

        val captor = argumentCaptor<Joke>()

        verify(mockView, times(2)).addJoke(captor.capture())

        assertEquals("Chuck Norris knows how to say souffle in the French language.", captor.firstValue.value)
        assertEquals("https://assets.chucknorris.host/img/avatar/chuck-norris.png", captor.firstValue.iconUrl)
        assertEquals("We have our fears, fear has its Chuck Norris'es", captor.secondValue.value)
        assertEquals("http://chuck.image.url", captor.secondValue.iconUrl)
    }

    @Test
    fun `should hide empty case when there are results`() {
        givenTheApiReturns(someJokes)

        runBlocking {
            presenter.initialize()

            presenter.onSearchButtonClicked("Jackie")
        }

        verify(mockView).hideEmptyCase()
    }

    private fun givenTheApiReturns(jokes: List<Joke>) {
        whenever(mockChuckNorrisRepository.getRandomJokeByKeyword(anyString())).thenReturn(Pair(jokes, null))
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
