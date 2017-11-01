package es.voghdev.chucknorrisjokes.ui.presenter

import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.app.coroutine
import es.voghdev.chucknorrisjokes.app.success
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository

class JokeByKeywordPresenter(val context: ResLocator, val chuckNorrisRepository: ChuckNorrisRepository) :
        Presenter<JokeByKeywordPresenter.MVPView, JokeByKeywordPresenter.Navigator>() {

    override suspend fun initialize() {

    }

    suspend fun onSearchButtonClicked(text: String) {
        if (text.isNotEmpty()) {
            coroutine {
                chuckNorrisRepository.getRandomJokeByKeyword(text)
            }.await().let { result ->
                if (result.success()) {
                    if (result.first?.isNotEmpty() ?: false) {
                        view?.showJokeText(result.first?.elementAt(0)?.value ?: "")
                        view?.showJokeImage(result.first?.elementAt(0)?.iconUrl ?: "")
                    } else {
                        view?.showEmptyCase()
                    }
                }
            }
        } else {
            view?.showKeywordError("You must enter a keyword")
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
