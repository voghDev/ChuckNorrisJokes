package es.voghdev.chucknorrisjokes.ui.presenter

import arrow.core.Either
import arrow.core.Right
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import es.voghdev.chucknorrisjokes.anyCategory
import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.model.Joke
import es.voghdev.chucknorrisjokes.model.JokeCategory
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import io.kotlintest.mock.mock
import io.kotlintest.specs.StringSpec
import kotlinx.coroutines.Dispatchers
import org.junit.Assert
import org.mockito.ArgumentMatchers

class JokeByCategoryPresenterTest : StringSpec(
    {
        val mockResLocator: ResLocator = mock()

        val mockNavigator: JokeByCategoryPresenter.Navigator = mock()

        val mockView: JokeByCategoryPresenter.MVPView = mock()

        val mockChuckNorrisRepository: ChuckNorrisRepository = mock()

        val presenter: JokeByCategoryPresenter = JokeByCategoryPresenter(Dispatchers.Unconfined, mockResLocator, mockChuckNorrisRepository).apply {
            view = mockView
            navigator = mockNavigator
        }

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

        "should request all available categories on start"{
            givenThereAreSomeCategories(mockChuckNorrisRepository, categories)

            presenter.initialize()

            verify(mockChuckNorrisRepository).getJokeCategories()
        }

        "should fill category names in a spinner when categories are received"{
            givenThereAreSomeCategories(mockChuckNorrisRepository, categories)

            presenter.initialize()

            verify(mockView).fillCategories(ArgumentMatchers.anyList())
        }

        "should perform search with selected category when Search button is clicked"{
            givenThereAreSomeCategories(mockChuckNorrisRepository, categories)
            givenTheRepositoryHasAnExampleJoke(mockChuckNorrisRepository, exampleJoke)

            presenter.initialize()

            presenter.onSearchButtonClicked(0)

            verify(mockChuckNorrisRepository).getRandomJokeByCategory(categoryCaptor.capture())
        }

        "should show the joke's text when a joke is receved"{
            givenThereAreSomeCategories(mockChuckNorrisRepository, categories)
            givenTheRepositoryHasAnExampleJoke(mockChuckNorrisRepository, exampleJoke)

            presenter.initialize()

            presenter.onSearchButtonClicked(0)

            verify(mockView).showJokeText(strCaptor.capture())

            Assert.assertEquals("We have our fears, fear has its Chuck Norris'es", strCaptor.firstValue)
        }

        "should show the joke's image when a joke is received"{
            givenThereAreSomeCategories(mockChuckNorrisRepository, categories)
            givenTheRepositoryHasAnExampleJoke(mockChuckNorrisRepository, exampleJoke)

            presenter.initialize()

            presenter.onSearchButtonClicked(0)

            verify(mockView).showJokeImage(strCaptor.capture())

            Assert.assertEquals("http://chuck.image.url", strCaptor.firstValue)
        }
    }
)

private fun givenTheRepositoryHasAnExampleJoke(repository: ChuckNorrisRepository, exampleJoke: Joke) {
    whenever(repository.getRandomJokeByCategory(anyCategory())).thenReturn(Either.Right(exampleJoke))
}

private fun givenThereAreSomeCategories(repository: ChuckNorrisRepository, categories: List<JokeCategory>) {
    whenever(repository.getJokeCategories()).thenReturn(Right(categories))
}