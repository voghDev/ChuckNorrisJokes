package es.voghdev.chucknorrisjokes.ui.presenter

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.model.Joke
import es.voghdev.chucknorrisjokes.model.JokeCategory
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import io.kotlintest.specs.StringSpec
import kotlinx.coroutines.Dispatchers

class JokeByCategoryPresenterTest : StringSpec(
    {
        val mockResLocator: ResLocator = mock()

        val mockNavigator: JokeByCategoryPresenter.Navigator = mock()

        val mockView: JokeByCategoryPresenter.MVPView = mock()

        val mockChuckNorrisRepository: ChuckNorrisRepository = mock()

        fun createMockedPresenter(): JokeByCategoryPresenter {
            val presenter = JokeByCategoryPresenter(Dispatchers.Unconfined, mockResLocator, mockChuckNorrisRepository)
            presenter.view = mockView
            presenter.navigator = mockNavigator
            return presenter
        }

        val presenter = createMockedPresenter()

        val categories = listOf(
            JokeCategory("Politics"),
            JokeCategory("Sports")
        )

        val exampleJoke = Joke(id = "abc",
                               iconUrl = "http://chuck.image.url",
                               url = "http://example.url",
                               value = "We have our fears, fear has its Chuck Norris'es")

        val categoryCaptor = argumentCaptor<JokeCategory>()
        val strCaptor = argumentCaptor<String>()
    }
)