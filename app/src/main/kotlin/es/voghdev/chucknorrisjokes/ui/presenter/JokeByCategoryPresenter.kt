package es.voghdev.chucknorrisjokes.ui.presenter

import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository

class JokeByCategoryPresenter(val context: ResLocator, val repository: ChuckNorrisRepository) :
        Presenter<JokeByCategoryPresenter.MVPView, JokeByCategoryPresenter.Navigator>() {

    override suspend fun initialize() {

    }

    interface MVPView {

    }

    interface Navigator {

    }
}
