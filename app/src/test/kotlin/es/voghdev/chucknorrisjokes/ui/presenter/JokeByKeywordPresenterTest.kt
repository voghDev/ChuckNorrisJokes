package es.voghdev.chucknorrisjokes.ui.presenter

import arrow.core.Either
import arrow.core.Right
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.model.CNError
import es.voghdev.chucknorrisjokes.model.Joke
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import io.kotlintest.mock.mock
import io.kotlintest.specs.StringSpec
import kotlinx.coroutines.Dispatchers
import org.junit.Assert
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

class JokeByKeywordPresenterTest : StringSpec(
    {
        val mockResLocator: ResLocator = mock()

        val mockNavigator: JokeByKeywordPresenter.Navigator = mock()

        val mockView: JokeByKeywordPresenter.MVPView = mock()

        val mockChuckNorrisRepository: ChuckNorrisRepository = mock()

        val presenter: JokeByKeywordPresenter = JokeByKeywordPresenter(Dispatchers.Unconfined, mockResLocator, mockChuckNorrisRepository).apply {
            view = mockView
            navigator = mockNavigator
        }

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

        "should not accept an empty text as search keyword" {
            presenter.initialize()

            presenter.onSearchButtonClicked("")

            verify(mockView).showKeywordError("You must enter a keyword")
        }

        "should request a random joke by keyword when  search button is clicked with a valid keyword" {
            givenTheApiReturnsNoResults(mockChuckNorrisRepository)

            presenter.initialize()

            presenter.onSearchButtonClicked("lee")

            verify(mockChuckNorrisRepository).getRandomJokeByKeyword("lee")
        }

        "should show an empty case when an empty list is returned by the API" {
            givenTheApiReturnsNoResults(mockChuckNorrisRepository)

            presenter.initialize()

            presenter.onSearchButtonClicked("this query returns no results")

            verify(mockView).showEmptyCase()
        }

        "should add the first two jokes to the list when a list of two jokes is returned by the API" {
            givenTheApiReturns(mockChuckNorrisRepository, someJokes)

            presenter.initialize()

            presenter.onSearchButtonClicked("chan")

            val captor = argumentCaptor<Joke>()

            verify(mockView, Mockito.times(2)).addJoke(captor.capture())

            Assert.assertEquals("Chuck Norris knows how to say souffle in the French language.", captor.firstValue.value)
            Assert.assertEquals("https://assets.chucknorris.host/img/avatar/chuck-norris.png", captor.firstValue.iconUrl)
            Assert.assertEquals("We have our fears, fear has its Chuck Norris'es", captor.secondValue.value)
            Assert.assertEquals("http://chuck.image.url", captor.secondValue.iconUrl)
        }

        "should hide empty case when there are results" {
            givenTheApiReturns(mockChuckNorrisRepository, someJokes)

            presenter.initialize()

            presenter.onSearchButtonClicked("Jackie")

            verify(mockView).hideEmptyCase()
        }

        "should show an error when Api returns an error" {
            givenTheApiReturnsAnError(mockChuckNorrisRepository, "422 unprocessable Entity")

            presenter.initialize()

            presenter.onSearchButtonClicked("erroneous search")

            verify(mockView).showError("422 unprocessable Entity")
        }
    }
)

private fun givenTheApiReturnsAnError(repository: ChuckNorrisRepository, message: String) {
    whenever(repository.getRandomJokeByKeyword(ArgumentMatchers.anyString())).thenReturn(Either.Left(CNError(message)))
}

private fun givenTheApiReturns(repository: ChuckNorrisRepository, jokes: List<Joke>) {
    whenever(repository.getRandomJokeByKeyword(ArgumentMatchers.anyString())).thenReturn(Right(jokes))
}

private fun givenTheApiReturnsNoResults(repository: ChuckNorrisRepository) {
    whenever(repository.getRandomJokeByKeyword(ArgumentMatchers.anyString())).thenReturn(Right(emptyList()))
}
