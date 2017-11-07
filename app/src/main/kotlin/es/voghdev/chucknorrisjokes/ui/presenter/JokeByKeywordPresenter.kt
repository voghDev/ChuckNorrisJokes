package es.voghdev.chucknorrisjokes.ui.presenter

import es.voghdev.chucknorrisjokes.app.*
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
            view?.showJokeText(result.firstJoke().value)
            view?.showJokeImage(result.firstJoke().iconUrl)
        } else if (result.success()) {
            view?.showEmptyCase()
        }
    }

    interface MVPView {
        fun showKeywordError(msg: String)
        fun showEmptyCase()
        fun showJokeText(text: String)
        fun showJokeImage(url: String)
    }

    interface Navigator {

    }
}
