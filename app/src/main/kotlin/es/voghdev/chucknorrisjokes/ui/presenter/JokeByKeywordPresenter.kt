package es.voghdev.chucknorrisjokes.ui.presenter

import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.app.coroutine
import es.voghdev.chucknorrisjokes.app.hasResults
import es.voghdev.chucknorrisjokes.app.success
import es.voghdev.chucknorrisjokes.model.Joke
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository

class JokeByKeywordPresenter(val resLocator: ResLocator, val repository: ChuckNorrisRepository) :
        Presenter<JokeByKeywordPresenter.MVPView, JokeByKeywordPresenter.Navigator>() {

    override suspend fun initialize() {

    }

    suspend fun onSearchButtonClicked(text: String) {
        if (text.isEmpty()) {
            view?.showKeywordError("You must enter a keyword")
            return
        }

        val task = coroutine {
            repository.getRandomJokeByKeyword(text)
        }
        val result = task.await()
        if (result.hasResults()) {
            view?.hideEmptyCase()
            result.first?.forEach { joke ->
                view?.addJoke(joke)
            }
        } else if (result.success()) {
            view?.showEmptyCase()
        }
    }

    interface MVPView {
        fun showKeywordError(msg: String)
        fun showEmptyCase()
        fun hideEmptyCase()
        fun addJoke(joke: Joke)
    }

    interface Navigator {

    }
}
