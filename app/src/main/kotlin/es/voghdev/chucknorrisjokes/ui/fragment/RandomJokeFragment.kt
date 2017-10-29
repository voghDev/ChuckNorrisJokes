package es.voghdev.chucknorrisjokes.ui.fragment

import android.os.Bundle
import es.voghdev.chucknorrisjokes.R
import es.voghdev.chucknorrisjokes.app.AndroidResLocator
import es.voghdev.chucknorrisjokes.datasource.mock.GetRandomJokeMockImpl
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import es.voghdev.chucknorrisjokes.ui.presenter.RandomJokePresenter


class RandomJokeFragment : BaseFragment(), RandomJokePresenter.MVPView, RandomJokePresenter.Navigator {

    var presenter: RandomJokePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = ChuckNorrisRepository(GetRandomJokeMockImpl())

        presenter = RandomJokePresenter(AndroidResLocator(context), repository)
        presenter?.view = this
        presenter?.navigator = this

        presenter?.initialize()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_random_joke
    }
}
