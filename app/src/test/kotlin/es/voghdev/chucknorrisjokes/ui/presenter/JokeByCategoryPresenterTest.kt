package es.voghdev.chucknorrisjokes

import es.voghdev.chucknorrisjokes.ui.presenter.Presenter
import es.voghdev.chucknorrisjokes.repository.UserRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import android.content.Context

class JokeByCategoryPresenterTest() {


    @Mock lateinit var mockContext: Context

    @Mock lateinit var mockNavigator: JokeByCategoryPresenter.Navigator

    @Mock lateinit var mockView: JokeByCategoryPresenter.MVPView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    private fun createMockedPresenter(): JokeByCategoryPresenter {
        val presenter = JokeByCategoryPresenter(mockContext)
        presenter.view = mockView
        presenter.navigator = mockNavigator
        return presenter
    }
}
