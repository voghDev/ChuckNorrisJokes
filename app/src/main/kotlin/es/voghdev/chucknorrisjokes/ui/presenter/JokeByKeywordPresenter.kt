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
import es.voghdev.chucknorrisjokes.app.hasResults
import es.voghdev.chucknorrisjokes.app.success
import es.voghdev.chucknorrisjokes.model.Joke
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository

class JokeByKeywordPresenter(val resLocator: ResLocator, val repository: ChuckNorrisRepository) :
    Presenter<JokeByKeywordPresenter.MVPView, JokeByKeywordPresenter.Navigator>() {

    override suspend fun initialize() {

    }

    suspend fun onSearchButtonClicked(text: String) {
        if (text.isEmpty()) {
            view?.showKeywordError("You must enter a keyword")
            return
        }

        if (text.length <= 2) {
            view?.showKeywordError("Keyword must have 2 characters at least")
            return
        }

        val result = coroutine { repository.getRandomJokeByKeyword(text) }.await()

        if (result.hasResults()) {
            view?.hideEmptyCase()
            (result as Either.Right).b.forEach { joke ->
                view?.addJoke(joke)
            }
        } else if (result.success()) {
            view?.showEmptyCase()
        } else {
            view?.showError((result as? Either.Left)?.a?.message() ?: "Unknown error")
        }
    }

    interface MVPView {
        fun showKeywordError(msg: String)
        fun showEmptyCase()
        fun hideEmptyCase()
        fun addJoke(joke: Joke)
        fun showError(msg: String)
    }

    interface Navigator {

    }
}
