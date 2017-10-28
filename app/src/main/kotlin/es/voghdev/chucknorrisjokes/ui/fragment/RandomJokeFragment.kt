package es.voghdev.chucknorrisjokes.ui.fragment

import android.os.Bundle
import es.voghdev.chucknorrisjokes.R
import es.voghdev.chucknorrisjokes.app.AndroidResLocator


class RandomJokeFragment : BaseFragment(), RandomJokePresenter.MVPView, RandomJokePresenter.Navigator {

    var presenter: RandomJokePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = RandomJokePresenter(AndroidResLocator(context))
        presenter?.view = this
        presenter?.navigator = this

        presenter?.initialize()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_random_joke
    }
}
