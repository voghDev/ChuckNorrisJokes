package es.voghdev.chucknorrisjokes

import android.os.Bundle
import es.voghdev.chucknorrisjokes.ui.activity.BaseActivity

class MainActivity : BaseActivity(), MainPresenter.MVPView, MainPresenter.Navigator {

    var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = MainPresenter(this)
        presenter?.view = this
        presenter?.navigator = this

        presenter?.initialize()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }
}
