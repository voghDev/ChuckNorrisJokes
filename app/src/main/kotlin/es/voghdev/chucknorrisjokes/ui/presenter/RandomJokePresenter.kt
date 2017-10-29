package es.voghdev.chucknorrisjokes.ui.presenter

import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.app.coroutine
import es.voghdev.chucknorrisjokes.app.success
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository

class RandomJokePresenter(val resLocator: ResLocator, val repository: ChuckNorrisRepository) :
        Presenter<RandomJokePresenter.MVPView, RandomJokePresenter.Navigator>() {

    override fun initialize() {
        coroutine {
            repository.getRandomJoke()
        }.await().let { result ->
            if (result.success()) {
                view?.showJokeText(result.first?.value ?: "")
            }
        }
    }

    interface MVPView {
        fun showJokeText(text: String)

    }

    interface Navigator {

    }
}
