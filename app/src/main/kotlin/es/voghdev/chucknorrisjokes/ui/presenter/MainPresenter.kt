package es.voghdev.chucknorrisjokes.ui.presenter

import es.voghdev.chucknorrisjokes.app.ResLocator

class MainPresenter(val resLocator: ResLocator) : Presenter<MainPresenter.MVPView, MainPresenter.Navigator>() {

    override fun initialize() {
        view?.configureTabs()
    }

    interface MVPView {
        fun configureTabs()
    }

    interface Navigator {

    }
}
