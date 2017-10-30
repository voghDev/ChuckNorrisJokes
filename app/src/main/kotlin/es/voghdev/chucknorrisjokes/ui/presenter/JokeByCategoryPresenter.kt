package es.voghdev.chucknorrisjokes.ui.presenter

import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.app.success
import es.voghdev.chucknorrisjokes.model.JokeCategory
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository

class JokeByCategoryPresenter(val context: ResLocator, val chuckNorrisRepository: ChuckNorrisRepository) :
        Presenter<JokeByCategoryPresenter.MVPView, JokeByCategoryPresenter.Navigator>() {

    override suspend fun initialize() {
        val result = chuckNorrisRepository.getJokeCategories()

        if (result.success()) {
            view?.fillCategories(result.first ?: emptyList())
        }
    }

    interface MVPView {
        fun fillCategories(list: List<JokeCategory>)

    }

    interface Navigator {

    }
}
