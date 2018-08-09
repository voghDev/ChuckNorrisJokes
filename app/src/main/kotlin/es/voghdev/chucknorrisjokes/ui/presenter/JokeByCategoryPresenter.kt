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
package es.voghdev.chucknorrisjokes.ui.presenter

import arrow.core.Either
import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.app.coroutine
import es.voghdev.chucknorrisjokes.app.success
import es.voghdev.chucknorrisjokes.model.JokeCategory
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository

class JokeByCategoryPresenter(val resLocator: ResLocator, val repository: ChuckNorrisRepository) :
    Presenter<JokeByCategoryPresenter.MVPView, JokeByCategoryPresenter.Navigator>() {

    var categories: List<JokeCategory> = emptyList()

    override suspend fun initialize() {
        val task = coroutine {
            repository.getJokeCategories()
        }
        val result = task.await()

        if (result.success()) {
            categories = (result as Either.Right).b
            view?.fillCategories(categories)
        }
    }

    suspend fun onSearchButtonClicked(position: Int) {
        coroutine {
            repository.getRandomJokeByCategory(categories[position])
        }.await().let { result ->
            if (result.success()) {
                view?.showJokeText((result as Either.Right).b.value)
                view?.showJokeImage((result as Either.Right).b.iconUrl)
            }
        }
    }

    interface MVPView {
        fun fillCategories(list: List<JokeCategory>)
        fun showJokeText(text: String)
        fun showJokeImage(url: String)
    }

    interface Navigator {

    }
}
