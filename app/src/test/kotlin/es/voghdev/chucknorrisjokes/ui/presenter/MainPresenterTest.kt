package es.voghdev.chucknorrisjokes.ui.presenter

import com.nhaarman.mockitokotlin2.mock
import es.voghdev.chucknorrisjokes.app.ResLocator
import io.kotlintest.specs.StringSpec

class MainPresenterTest : StringSpec(
    {
        val mockResLocator: ResLocator = mock()

        val mockNavigator: MainPresenter.Navigator = mock()

        val mockView: MainPresenter.MVPView = mock()

        fun createMockedPresenter(): MainPresenter {
            val presenter = MainPresenter(mockResLocator)
            presenter.view = mockView
            presenter.navigator = mockNavigator
            return presenter
        }

        val presenter = createMockedPresenter()
    }
)