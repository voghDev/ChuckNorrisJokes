package es.voghdev.chucknorrisjokes.ui.presenter

import es.voghdev.chucknorrisjokes.app.ResLocator

class RandomJokePresenter(val context: ResLocator) :
        Presenter<RandomJokePresenter.MVPView, RandomJokePresenter.Navigator>() {

    override fun initialize() {

    }

    interface MVPView {

    }

    interface Navigator {

    }
}
