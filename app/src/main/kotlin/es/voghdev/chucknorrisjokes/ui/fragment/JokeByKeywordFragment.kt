/*
 * Copyright (C) 2018 Olmo Gallegos Hernández.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.voghdev.chucknorrisjokes.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import es.voghdev.chucknorrisjokes.R
import es.voghdev.chucknorrisjokes.app.AndroidResLocator
import es.voghdev.chucknorrisjokes.app.ui
import es.voghdev.chucknorrisjokes.datasource.api.GetJokeCategoriesApiImpl
import es.voghdev.chucknorrisjokes.datasource.api.GetRandomJokeApiImpl
import es.voghdev.chucknorrisjokes.datasource.api.GetRandomJokeByCategoryApiImpl
import es.voghdev.chucknorrisjokes.datasource.api.GetRandomJokeByKeywordApiImpl
import es.voghdev.chucknorrisjokes.model.Joke
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import es.voghdev.chucknorrisjokes.ui.adapter.JokeAdapter
import es.voghdev.chucknorrisjokes.ui.presenter.JokeByKeywordPresenter
import kotlinx.android.synthetic.main.fragment_joke_by_keyword.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.toast

class JokeByKeywordFragment : BaseFragment(), JokeByKeywordPresenter.MVPView, JokeByKeywordPresenter.Navigator {
    var presenter: JokeByKeywordPresenter? = null
    var adapter: JokeAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chuckNorrisRepository = ChuckNorrisRepository(
            GetRandomJokeApiImpl(),
            GetJokeCategoriesApiImpl(),
            GetRandomJokeByKeywordApiImpl(),
            GetRandomJokeByCategoryApiImpl()
        )

        presenter = JokeByKeywordPresenter(AndroidResLocator(requireContext()), chuckNorrisRepository)
        presenter?.view = this
        presenter?.navigator = this

        adapter = JokeAdapter(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        launch(CommonPool) {
            presenter?.initialize()
        }

        btn_search.setOnClickListener {
            val keyword = et_keyword.text?.toString()?.trim() ?: ""
            launch(CommonPool) {
                presenter?.onSearchButtonClicked(keyword)
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_joke_by_keyword
    }

    override fun showKeywordError(msg: String) = ui {
        requireActivity().toast(msg)
    }

    override fun showEmptyCase() = ui {
        tv_empty_case.visibility = VISIBLE
        tv_empty_case.text = "This search returned no results"

        recyclerView.visibility = INVISIBLE
    }

    override fun hideEmptyCase() = ui {
        tv_empty_case.visibility = INVISIBLE

        recyclerView.visibility = VISIBLE
    }

    override fun addJoke(joke: Joke) = ui {
        adapter?.add(joke)

        adapter?.notifyDataSetChanged()
    }
}
