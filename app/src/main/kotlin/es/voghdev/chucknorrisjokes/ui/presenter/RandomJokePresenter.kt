package es.voghdev.chucknorrisjokes.ui.presenter

import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.app.coroutine
import es.voghdev.chucknorrisjokes.app.hasImage
import es.voghdev.chucknorrisjokes.app.success
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository

class RandomJokePresenter(val resLocator: ResLocator, val repository: ChuckNorrisRepository) :
        Presenter<RandomJokePresenter.MVPView, RandomJokePresenter.Navigator>() {

    override suspend fun initialize() {
        val task = coroutine {
            repository.getRandomJoke()
        }

        val result = task.await()
        if (result.success()) {
            view?.showJokeText(result.first?.value ?: "")
        }

        if (result.hasImage()) {
            view?.loadJokeImage(result.first?.iconUrl ?: "")
        }
    }

    interface MVPView {
        fun showJokeText(text: String)
        fun loadJokeImage(url: String)
    }

    interface Navigator {

    }
}
