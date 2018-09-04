package es.voghdev.chucknorrisjokes.ui.presenter

import com.nhaarman.mockito_kotlin.mock
import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import io.kotlintest.specs.StringSpec

class JokeByKeywordPresenterTest : StringSpec(
    {
        val mockResLocator: ResLocator = mock()

        val mockNavigator: JokeByKeywordPresenter.Navigator = mock()

        val mockView: JokeByKeywordPresenter.MVPView = mock()

        val mockChuckNorrisRepository: ChuckNorrisRepository = mock()

        fun createMockedPresenter(): JokeByKeywordPresenter {
            val presenter = JokeByKeywordPresenter(mockResLocator, mockChuckNorrisRepository)
            presenter.view = mockView
            presenter.navigator = mockNavigator
            return presenter
        }

        val presenter = createMockedPresenter()
    })