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
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

class RandomJokePresenterTest {
    @Mock
    lateinit var mockResLocator: ResLocator

    @Mock
    lateinit var mockNavigator: RandomJokePresenter.Navigator

    @Mock
    lateinit var mockView: RandomJokePresenter.MVPView

    @Mock
    lateinit var mockChuckNorrisRepository: ChuckNorrisRepository

    lateinit var presenter: RandomJokePresenter

    val exampleJoke = Joke(
        id = "GdEH64AkS9qEQCmqMwM2Rg",
        iconUrl = "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
        url = "http://api.chucknorris.io/jokes/GdEH64AkS9qEQCmqMwM2Rg",
        value = "Chuck Norris knows how to say souffle in the French language."
    )

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = createMockedPresenter()
    }

    @Test
    fun `should request a random joke on start`() {
        givenThereIsARandomJoke(exampleJoke)

        runBlocking {
            presenter.initialize()
        }

        verify(mockChuckNorrisRepository).getRandomJoke()
    }

    private fun givenThereIsARandomJoke(joke: Joke) {
        whenever(mockChuckNorrisRepository.getRandomJoke()).thenReturn(Pair(joke, null))
    }

    @Test
    fun `should show the joke's text on screen when a random joke is received`() {
        givenThereIsARandomJoke(exampleJoke)

        runBlocking {
            presenter.initialize()
        }

        verify(mockView).showJokeText(anyString())
    }

    @Test
    fun `should show the joke's image on screen when a random joke is received`() {
        givenThereIsARandomJoke(exampleJoke)

        runBlocking {
            presenter.initialize()
        }

        verify(mockView).loadJokeImage("https://assets.chucknorris.host/img/avatar/chuck-norris.png")
    }

    @Test
    fun `should not load the joke's image if it's empty`() {
        val jokeWithoutImage = exampleJoke.copy(iconUrl = "")

        givenThereIsARandomJoke(jokeWithoutImage)

        runBlocking {
            presenter.initialize()
        }

        verify(mockView, times(0)).loadJokeImage(anyString())
    }

    private fun createMockedPresenter(): RandomJokePresenter {
        val presenter = RandomJokePresenter(mockResLocator, mockChuckNorrisRepository)
        presenter.view = mockView
        presenter.navigator = mockNavigator
        return presenter
    }
}
