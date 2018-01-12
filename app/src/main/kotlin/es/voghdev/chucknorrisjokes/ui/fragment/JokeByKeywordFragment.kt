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
import es.voghdev.chucknorrisjokes.datasource.api.GetJokeCategoriesApiImpl
import es.voghdev.chucknorrisjokes.datasource.api.GetRandomJokeApiImpl
import es.voghdev.chucknorrisjokes.datasource.api.GetRandomJokeByCategoryApiImpl
import es.voghdev.chucknorrisjokes.datasource.api.GetRandomJokeByKeywordApiImpl
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository
import es.voghdev.chucknorrisjokes.ui.presenter.JokeByKeywordPresenter
import kotlinx.android.synthetic.main.fragment_joke_by_keyword.*
import kotlinx.coroutines.experimental.runBlocking
import org.jetbrains.anko.toast

class JokeByKeywordFragment : BaseFragment(), JokeByKeywordPresenter.MVPView, JokeByKeywordPresenter.Navigator {
    var presenter: JokeByKeywordPresenter? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chuckNorrisRepository = ChuckNorrisRepository(
                GetRandomJokeApiImpl(),
                GetJokeCategoriesApiImpl(),
                GetRandomJokeByKeywordApiImpl(),
                GetRandomJokeByCategoryApiImpl()
        )

        presenter = JokeByKeywordPresenter(AndroidResLocator(context), chuckNorrisRepository)
        presenter?.view = this
        presenter?.navigator = this

        runBlocking {
            presenter?.initialize()
        }

        btn_search.setOnClickListener {
            val text = et_keyword.text?.toString()?.trim() ?: ""
            runBlocking {
                presenter?.onSearchButtonClicked(text)
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_joke_by_keyword
    }

    override fun showKeywordError(msg: String) {
        activity.toast(msg)
    }

    override fun showEmptyCase() {
        tv_text.text = "This search returned no results"
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
