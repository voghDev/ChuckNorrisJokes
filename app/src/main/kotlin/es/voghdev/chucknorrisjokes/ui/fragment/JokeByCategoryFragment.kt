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
import android.view.View
import com.squareup.picasso.Picasso
import es.voghdev.chucknorrisjokes.R
import es.voghdev.chucknorrisjokes.app.AndroidResLocator
import es.voghdev.chucknorrisjokes.app.configureDefaultAdapter
import es.voghdev.chucknorrisjokes.app.ui
import es.voghdev.chucknorrisjokes.datasource.api.GetJokeCategoriesApiImpl
import es.voghdev.chucknorrisjokes.datasource.api.GetRandomJokeApiImpl
import es.voghdev.chucknorrisjokes.datasource.api.GetRandomJokeByCategoryApiImpl
import es.voghdev.chucknorrisjokes.datasource.api.GetRandomJokeByKeywordApiImpl
import es.voghdev.chucknorrisjokes.model.JokeCategory
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import es.voghdev.chucknorrisjokes.ui.presenter.JokeByCategoryPresenter
import kotlinx.android.synthetic.main.fragment_joke_by_category.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch

class JokeByCategoryFragment : BaseFragment(), JokeByCategoryPresenter.MVPView, JokeByCategoryPresenter.Navigator {
    var presenter: JokeByCategoryPresenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chuckNorrisRepository = ChuckNorrisRepository(
                GetRandomJokeApiImpl(),
                GetJokeCategoriesApiImpl(),
                GetRandomJokeByKeywordApiImpl(),
                GetRandomJokeByCategoryApiImpl()
        )

        presenter = JokeByCategoryPresenter(AndroidResLocator(requireContext()), chuckNorrisRepository)
        presenter?.view = this
        presenter?.navigator = this

        launch(CommonPool) {
            presenter?.initialize()
        }

        btn_search.setOnClickListener {
            launch(CommonPool) {
                presenter?.onSearchButtonClicked(spn_categories.selectedItemPosition)
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_joke_by_category
    }

    override fun fillCategories(list: List<JokeCategory>) = ui {
        val values = list.map { it.name }
        spn_categories.configureDefaultAdapter(values)
    }

    override fun showJokeText(text: String) = ui {
        tv_text.text = text
    }

    override fun showJokeImage(url: String) = ui {
        Picasso.with(context)
                .load(url)
                .into(iv_image)
    }
}
