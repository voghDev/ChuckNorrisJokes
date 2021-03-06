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
import es.voghdev.chucknorrisjokes.R
import es.voghdev.chucknorrisjokes.app.AndroidResLocator
import es.voghdev.chucknorrisjokes.datasource.api.GetJokeCategoriesApiImpl
import es.voghdev.chucknorrisjokes.datasource.api.GetRandomJokeApiImpl
import es.voghdev.chucknorrisjokes.datasource.api.GetRandomJokeByCategoryApiImpl
import es.voghdev.chucknorrisjokes.datasource.api.GetRandomJokeByKeywordApiImpl
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import es.voghdev.chucknorrisjokes.ui.presenter.JokeByCategoryPresenter
import kotlinx.coroutines.Dispatchers

class JokeByCategoryFragment : BaseFragment(), JokeByCategoryPresenter.MVPView, JokeByCategoryPresenter.Navigator {

    var presenter: JokeByCategoryPresenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chuckNorrisRepository = ChuckNorrisRepository(
            GetRandomJokeApiImpl(),
            GetJokeCategoriesApiImpl(),
            GetRandomJokeByKeywordApiImpl(),
            GetRandomJokeByCategoryApiImpl())

        presenter = JokeByCategoryPresenter(Dispatchers.IO, AndroidResLocator(requireContext()), chuckNorrisRepository)
        presenter?.view = this
        presenter?.navigator = this

        presenter?.initialize()

//        btn_search.setOnClickListener {
//            launch(CommonPool {
//                presenter?.onSearchButtonClicked(spn_categories.selectedItemPosition)
//            }
//        }
    }

//    override fun fillCategoriesSpinner(categories: List<JokeCategory>) = ui {
//        val names = categories.map { c -> c.name }
//        spn_categories.configureDefaultAdapter(names)
//    }

    override fun getLayoutId(): Int = R.layout.fragment_joke_by_category
}
