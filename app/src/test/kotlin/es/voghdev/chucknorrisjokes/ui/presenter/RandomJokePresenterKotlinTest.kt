package es.voghdev.chucknorrisjokes.ui.presenter

import arrow.core.Either
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import es.voghdev.chucknorrisjokes.model.Joke
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import io.kotlintest.mock.mock
import io.kotlintest.specs.StringSpec
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.mockito.ArgumentMatchers.anyString

class RandomJokePresenterKotlinTest : StringSpec({
    val mockNavigator: RandomJokePresenter.Navigator = mock()

    val mockView: RandomJokePresenter.MVPView = mock()

    val mockChuckNorrisRepository: ChuckNorrisRepository = mock()

    val testCoroutineDispatcher = TestCoroutineDispatcher()

    val presenter: RandomJokePresenter = RandomJokePresenter(Dispatchers.Main, mockChuckNorrisRepository).apply {
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
        runBlockingTest {
            Dispatchers.setMain(testCoroutineDispatcher)
            givenThereIsARandomJoke(mockChuckNorrisRepository, exampleJoke)

            presenter.initialize()

            verify(mockChuckNorrisRepository).getRandomJoke()
            Dispatchers.resetMain()
        }
    }

    "should show the joke's text on screen when a random joke is received" {
        runBlockingTest {
            Dispatchers.setMain(testCoroutineDispatcher)
            givenThereIsARandomJoke(mockChuckNorrisRepository, exampleJoke)

            presenter.initialize()

            verify(mockView).showJokeText(anyString())
            Dispatchers.resetMain()
        }
    }

    "should show the joke's image on screen when a random joke is received" {
        runBlockingTest {
            Dispatchers.setMain(testCoroutineDispatcher)
            givenThereIsARandomJoke(mockChuckNorrisRepository, exampleJoke)

            presenter.initialize()

            verify(mockView).loadJokeImage("https://assets.chucknorris.host/img/avatar/chuck-norris.png")
            Dispatchers.resetMain()
        }
    }

    "should not load the joke's image if it's empty" {
        runBlockingTest {
            Dispatchers.setMain(testCoroutineDispatcher)
            val jokeWithoutImage = exampleJoke.copy(iconUrl = "")

            givenThereIsARandomJoke(mockChuckNorrisRepository, jokeWithoutImage)

            presenter.initialize()

            verify(mockView, times(0)).loadJokeImage(anyString())
            Dispatchers.resetMain()
        }
    }
})

private fun givenThereIsARandomJoke(mockChuckNorrisRepository: ChuckNorrisRepository, joke: Joke) {
    whenever(mockChuckNorrisRepository.getRandomJoke()).thenReturn(Either.Right(joke))
}
