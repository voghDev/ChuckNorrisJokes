package es.voghdev.chucknorrisjokes.ui.presenter

import com.nhaarman.mockitokotlin2.mock
import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.model.Joke
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import io.kotlintest.specs.StringSpec
import kotlinx.coroutines.Dispatchers

class RandomJokePresenterTest : StringSpec(
    {
        val mockResLocator: ResLocator = mock()

        val mockNavigator: RandomJokePresenter.Navigator = mock()

        val mockView: RandomJokePresenter.MVPView = mock()

        val mockChuckNorrisRepository: ChuckNorrisRepository = mock()

        fun createMockedPresenter(): RandomJokePresenter {
            val presenter = RandomJokePresenter(Dispatchers.Unconfined, mockResLocator, mockChuckNorrisRepository)
            presenter.view = mockView
            presenter.navigator = mockNavigator
            return presenter
        }

        val presenter = createMockedPresenter()

        val exampleJoke = Joke(
            id = "GdEH64AkS9qEQCmqMwM2Rg",
            iconUrl = "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
            url = "http://api.chucknorris.io/jokes/GdEH64AkS9qEQCmqMwM2Rg",
            value = "Chuck Norris knows how to say souffle in the French language."
        )
    }
)