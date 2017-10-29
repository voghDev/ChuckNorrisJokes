package es.voghdev.chucknorrisjokes.ui.presenter

import es.voghdev.chucknorrisjokes.app.ResLocator

class JokeByCategoryPresenter(val context: ResLocator) :
        Presenter<JokeByCategoryPresenter.MVPView, JokeByCategoryPresenter.Navigator>() {

    override suspend fun initialize() {

    }

    interface MVPView {

    }

    interface Navigator {

    }
}
