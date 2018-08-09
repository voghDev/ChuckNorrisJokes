package es.voghdev.chucknorrisjokes.ui.presenter

import com.nhaarman.mockito_kotlin.argumentCaptor
import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.model.Joke
import es.voghdev.chucknorrisjokes.model.JokeCategory
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class JokeByCategoryPresenterTest() {
    @Mock
    lateinit var mockResLocator: ResLocator

    @Mock
    lateinit var mockNavigator: JokeByCategoryPresenter.Navigator

    @Mock
    lateinit var mockView: JokeByCategoryPresenter.MVPView

    lateinit var presenter: JokeByCategoryPresenter

    @Mock
    lateinit var mockChuckNorrisRepository: ChuckNorrisRepository

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

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = createMockedPresenter()
    }

    private fun createMockedPresenter(): JokeByCategoryPresenter {
        val presenter = JokeByCategoryPresenter(mockResLocator, mockChuckNorrisRepository)
        presenter.view = mockView
        presenter.navigator = mockNavigator
        return presenter
    }
}
