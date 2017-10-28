package es.voghdev.chucknorrisjokes.ui.fragment

import android.os.Bundle
import es.voghdev.chucknorrisjokes.R
import es.voghdev.chucknorrisjokes.app.AndroidResLocator
import es.voghdev.chucknorrisjokes.ui.presenter.JokeByCategoryPresenter

class JokeByCategoryFragment : BaseFragment(), JokeByCategoryPresenter.MVPView, JokeByCategoryPresenter.Navigator {

    var presenter: JokeByCategoryPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = JokeByCategoryPresenter(AndroidResLocator(context))
        presenter?.view = this
        presenter?.navigator = this

        presenter?.initialize()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_joke_by_category
    }
}
