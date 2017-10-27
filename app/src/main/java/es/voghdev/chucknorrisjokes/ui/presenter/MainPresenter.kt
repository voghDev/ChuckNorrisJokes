package es.voghdev.chucknorrisjokes

import es.voghdev.chucknorrisjokes.ui.presenter.Presenter

import android.content.Context

class MainPresenter(val context: Context) : Presenter<MainPresenter.MVPView, MainPresenter.Navigator>() {

    override fun initialize() {

    }

    interface MVPView {

    }

    interface Navigator {

    }
}
