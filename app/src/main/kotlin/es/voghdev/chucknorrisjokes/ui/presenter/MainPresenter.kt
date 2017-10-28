package es.voghdev.chucknorrisjokes

import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.ui.presenter.Presenter

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
