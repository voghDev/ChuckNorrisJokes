package es.voghdev.chucknorrisjokes.ui.presenter

import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.app.coroutine
import es.voghdev.chucknorrisjokes.app.success
import es.voghdev.chucknorrisjokes.model.JokeCategory
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository

class JokeByCategoryPresenter(val resLocator: ResLocator, val repository: ChuckNorrisRepository) :
        Presenter<JokeByCategoryPresenter.MVPView, JokeByCategoryPresenter.Navigator>() {

    var categories: List<JokeCategory> = emptyList()

    override suspend fun initialize() {
        val task = coroutine {
            repository.getJokeCategories()
        }
        val result = task.await()

        if (result.success()) {
            categories = result.first ?: emptyList()
            view?.fillCategories(categories)
        }
    }

    suspend fun onSearchButtonClicked(position: Int) {
        coroutine {
            repository.getRandomJokeByCategory(categories[position])
        }.await().let { result ->
            if (result.success()) {
                view?.showJokeText(result.first?.value ?: "")
                view?.showJokeImage(result.first?.iconUrl ?: "")
            }
        }
    }

    interface MVPView {
        fun fillCategories(list: List<JokeCategory>)
        fun showJokeText(text: String)
        fun showJokeImage(url: String)
    }

    interface Navigator {

    }
}
