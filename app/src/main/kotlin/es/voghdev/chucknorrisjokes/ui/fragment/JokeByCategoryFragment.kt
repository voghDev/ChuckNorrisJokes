package es.voghdev.chucknorrisjokes.ui.fragment

import android.os.Bundle
import android.view.View
import es.voghdev.chucknorrisjokes.R
import es.voghdev.chucknorrisjokes.app.AndroidResLocator
import es.voghdev.chucknorrisjokes.ui.presenter.JokeByCategoryPresenter
import kotlinx.coroutines.experimental.runBlocking

class JokeByCategoryFragment : BaseFragment(), JokeByCategoryPresenter.MVPView, JokeByCategoryPresenter.Navigator {

    var presenter: JokeByCategoryPresenter? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = JokeByCategoryPresenter(AndroidResLocator(context))
        presenter?.view = this
        presenter?.navigator = this

        runBlocking {
            presenter?.initialize()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_joke_by_category
    }
}
