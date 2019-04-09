package es.voghdev.chucknorrisjokes.ui.presenter

import com.nhaarman.mockitokotlin2.mock
import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import io.kotlintest.specs.StringSpec
import kotlinx.coroutines.Dispatchers

class JokeByKeywordPresenterTest : StringSpec(
    {
        val mockResLocator: ResLocator = mock()

        val mockNavigator: JokeByKeywordPresenter.Navigator = mock()

        val mockView: JokeByKeywordPresenter.MVPView = mock()

        val mockChuckNorrisRepository: ChuckNorrisRepository = mock()

        fun createMockedPresenter(): JokeByKeywordPresenter {
            val presenter = JokeByKeywordPresenter(Dispatchers.Unconfined, mockResLocator, mockChuckNorrisRepository)
            presenter.view = mockView
            presenter.navigator = mockNavigator
            return presenter
        }

        val presenter = createMockedPresenter()
    })