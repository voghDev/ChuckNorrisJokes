package es.voghdev.chucknorrisjokes.ui.presenter

import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository

class JokeByKeywordPresenter(val context: ResLocator, val chuckNorrisRepository: ChuckNorrisRepository) :
        Presenter<JokeByKeywordPresenter.MVPView, JokeByKeywordPresenter.Navigator>() {

    override suspend fun initialize() {

    }

    interface MVPView {
        fun showKeywordError(msg: String)
        fun showEmptyCase()

    }

    interface Navigator {

    }

    fun onSearchButtonClicked(text: String) {
        if(text.isNotEmpty()) {
            chuckNorrisRepository.getRandomJokeByKeyword(text)
        } else {
            view?.showKeywordError("You must enter a keyword")
        }
    }
}
