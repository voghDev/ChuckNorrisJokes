package es.voghdev.chucknorrisjokes.ui.fragment

import android.os.Bundle
import android.view.View
import es.voghdev.chucknorrisjokes.R
import es.voghdev.chucknorrisjokes.app.AndroidResLocator
import es.voghdev.chucknorrisjokes.datasource.api.GetRandomJokeApiImpl
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import es.voghdev.chucknorrisjokes.ui.presenter.RandomJokePresenter
import kotlinx.coroutines.experimental.runBlocking


class RandomJokeFragment : BaseFragment(), RandomJokePresenter.MVPView, RandomJokePresenter.Navigator {

    var presenter: RandomJokePresenter? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = ChuckNorrisRepository(GetRandomJokeApiImpl())

        presenter = RandomJokePresenter(AndroidResLocator(context), repository)
        presenter?.view = this
        presenter?.navigator = this

        runBlocking {
            presenter?.initialize()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_random_joke
    }
}
