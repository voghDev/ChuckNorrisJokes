package es.voghdev.chucknorrisjokes.ui.presenter

import arrow.core.Either
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.model.Joke
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import io.kotlintest.mock.mock
import io.kotlintest.specs.StringSpec
import kotlinx.coroutines.experimental.runBlocking
import org.mockito.ArgumentMatchers.anyString

class RandomJokePresenterKotlinTest : StringSpec({
    val mockResLocator: ResLocator = mock()

    val mockNavigator: RandomJokePresenter.Navigator = mock()

    val mockView: RandomJokePresenter.MVPView = mock()

    val mockChuckNorrisRepository: ChuckNorrisRepository = mock()

    val presenter: RandomJokePresenter = RandomJokePresenter(mockResLocator, mockChuckNorrisRepository).apply {
        view = mockView
        navigator = mockNavigator
    }

    val exampleJoke = Joke(
        id = "GdEH64AkS9qEQCmqMwM2Rg",
        iconUrl = "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
        url = "http://api.chucknorris.io/jokes/GdEH64AkS9qEQCmqMwM2Rg",
        value = "Chuck Norris knows how to say souffle in the French language."
    )

    "should request a random joke on start" {
        givenThereIsARandomJoke(mockChuckNorrisRepository, exampleJoke)

        runBlocking {
            presenter.initialize()
        }

        verify(mockChuckNorrisRepository).getRandomJoke()
    }

    "should show the joke's text on screen when a random joke is received" {
        givenThereIsARandomJoke(mockChuckNorrisRepository, exampleJoke)

        runBlocking {
            presenter.initialize()
        }

        verify(mockView).showJokeText(anyString())
    }

    "should show the joke's image on screen when a random joke is received" {
        givenThereIsARandomJoke(mockChuckNorrisRepository, exampleJoke)

        runBlocking {
            presenter.initialize()
        }

        verify(mockView).loadJokeImage("https://assets.chucknorris.host/img/avatar/chuck-norris.png")
    }

    "should not load the joke's image if it's empty" {
        val jokeWithoutImage = exampleJoke.copy(iconUrl = "")

        givenThereIsARandomJoke(mockChuckNorrisRepository, jokeWithoutImage)

        runBlocking {
            presenter.initialize()
        }

        verify(mockView, times(0)).loadJokeImage(anyString())
    }
})

private fun givenThereIsARandomJoke(mockChuckNorrisRepository: ChuckNorrisRepository, joke: Joke) {
    whenever(mockChuckNorrisRepository.getRandomJoke()).thenReturn(Either.Right(joke))
}
