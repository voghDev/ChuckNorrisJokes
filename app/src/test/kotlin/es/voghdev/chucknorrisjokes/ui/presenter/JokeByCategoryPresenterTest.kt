package es.voghdev.chucknorrisjokes.ui.presenter

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import es.voghdev.chucknorrisjokes.anyCategory
import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.model.Joke
import es.voghdev.chucknorrisjokes.model.JokeCategory
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class JokeByCategoryPresenterTest() {
    @Mock lateinit var mockResLocator: ResLocator

    @Mock lateinit var mockNavigator: JokeByCategoryPresenter.Navigator

    @Mock lateinit var mockView: JokeByCategoryPresenter.MVPView

    @Mock lateinit var mockChuckNorrisRepository: ChuckNorrisRepository

    lateinit var presenter: JokeByCategoryPresenter

    val categories = listOf(
            JokeCategory("Politics"),
            JokeCategory("Sports")
    )

    val exampleJoke = Joke(id = "abc",
            iconUrl = "http://chuck.io",
            url = "http://example.url",
            value = "We have our fears, fear has its Chuck Norris'es")

    val categoryCaptor = argumentCaptor<JokeCategory>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = createMockedPresenter()
    }

    @Test
    fun `should request all available categories on start`() {
        givenThereAreSomeCategories(categories)

        runBlocking {
            presenter.initialize()
        }

        verify(mockChuckNorrisRepository).getJokeCategories()
    }

    @Test
    fun `should fill category names in a spinner when categories are received`() {
        givenThereAreSomeCategories(categories)

        runBlocking {
            presenter.initialize()
        }

        verify(mockView).fillCategories(anyList())
    }

    @Test
    fun `should perform search with selected category when "Search" button is clicked`() {
        givenThereAreSomeCategories(categories)
        givenTheRepositoryHasAnExampleJoke(exampleJoke)

        runBlocking {
            presenter.initialize()

            presenter.onSearchButtonClicked(0)
        }

        verify(mockChuckNorrisRepository).getRandomJokeByCategory(categoryCaptor.capture())
    }

    private fun givenTheRepositoryHasAnExampleJoke(exampleJoke: Joke) {
        whenever(mockChuckNorrisRepository.getRandomJokeByCategory(anyCategory())).thenReturn(Pair(exampleJoke, null))
    }

    private fun givenThereAreSomeCategories(categories: List<JokeCategory>) {
        whenever(mockChuckNorrisRepository.getJokeCategories()).thenReturn(Pair(categories, null))
    }

    private fun createMockedPresenter(): JokeByCategoryPresenter {
        val presenter = JokeByCategoryPresenter(mockResLocator, mockChuckNorrisRepository)
        presenter.view = mockView
        presenter.navigator = mockNavigator
        return presenter
    }
}
