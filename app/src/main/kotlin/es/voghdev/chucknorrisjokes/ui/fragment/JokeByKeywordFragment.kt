package es.voghdev.chucknorrisjokes.ui.fragment

import android.os.Bundle
import android.view.View
import es.voghdev.chucknorrisjokes.R
import es.voghdev.chucknorrisjokes.app.AndroidResLocator
import es.voghdev.chucknorrisjokes.datasource.api.GetJokeCategoriesApiImpl
import es.voghdev.chucknorrisjokes.datasource.api.GetRandomJokeApiImpl
import es.voghdev.chucknorrisjokes.datasource.api.GetRandomJokeByCategoryApiImpl
import es.voghdev.chucknorrisjokes.datasource.api.GetRandomJokeByKeywordApiImpl
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import es.voghdev.chucknorrisjokes.ui.adapter.JokeAdapter
import es.voghdev.chucknorrisjokes.ui.presenter.JokeByKeywordPresenter
import kotlinx.coroutines.experimental.runBlocking


class JokeByKeywordFragment : BaseFragment(), JokeByKeywordPresenter.MVPView, JokeByKeywordPresenter.Navigator {

    var presenter: JokeByKeywordPresenter? = null
//    var adapter: JokeAdapter? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chuckNorrisRepository = ChuckNorrisRepository(
                GetRandomJokeApiImpl(),
                GetJokeCategoriesApiImpl(),
                GetRandomJokeByKeywordApiImpl(),
                GetRandomJokeByCategoryApiImpl())

        presenter = JokeByKeywordPresenter(AndroidResLocator(context), chuckNorrisRepository)
        presenter?.view = this
        presenter?.navigator = this

//        adapter = JokeAdapter(context)
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        runBlocking {
            presenter?.initialize()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_joke_by_keyword
    }

//    override fun addJoke(joke: Joke) {
//        adapter?.add(joke)
//
//        adapter?.notifyDataSetChanged()
//    }
}
