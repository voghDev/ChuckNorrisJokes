package es.voghdev.chucknorrisjokes.ui.fragment

import android.os.Bundle
import android.view.View
import com.squareup.picasso.Picasso
import es.voghdev.chucknorrisjokes.R
import es.voghdev.chucknorrisjokes.app.AndroidResLocator
import es.voghdev.chucknorrisjokes.app.configureDefaultAdapter
import es.voghdev.chucknorrisjokes.datasource.api.GetJokeCategoriesApiImpl
import es.voghdev.chucknorrisjokes.datasource.api.GetRandomJokeApiImpl
import es.voghdev.chucknorrisjokes.datasource.api.GetRandomJokeByCategoryApiImpl
import es.voghdev.chucknorrisjokes.model.JokeCategory
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import es.voghdev.chucknorrisjokes.ui.presenter.JokeByCategoryPresenter
import kotlinx.android.synthetic.main.fragment_joke_by_category.*
import kotlinx.coroutines.experimental.runBlocking

class JokeByCategoryFragment : BaseFragment(), JokeByCategoryPresenter.MVPView, JokeByCategoryPresenter.Navigator {
    var presenter: JokeByCategoryPresenter? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chuckNorrisRepository = ChuckNorrisRepository(
                GetRandomJokeApiImpl(),
                GetJokeCategoriesApiImpl(),
                GetRandomJokeByCategoryApiImpl()
        )

        presenter = JokeByCategoryPresenter(AndroidResLocator(context), chuckNorrisRepository)
        presenter?.view = this
        presenter?.navigator = this

        runBlocking {
            presenter?.initialize()
        }

        btn_search.setOnClickListener {
            runBlocking {
                presenter?.onSearchButtonClicked(spn_categories.selectedItemPosition)
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_joke_by_category
    }

    override fun fillCategories(list: List<JokeCategory>) {
        val values = list.map { it.name }
        spn_categories.configureDefaultAdapter(values)
    }

    override fun showJokeText(text: String) {
        tv_text.text = text
    }

    override fun showJokeImage(url: String) {
        Picasso.with(context)
                .load(url)
                .into(iv_image)
    }
}
