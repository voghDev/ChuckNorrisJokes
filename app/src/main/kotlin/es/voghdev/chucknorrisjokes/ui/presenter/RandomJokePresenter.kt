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

import es.voghdev.chucknorrisjokes.app.ResLocator
import es.voghdev.chucknorrisjokes.app.coroutine
import es.voghdev.chucknorrisjokes.app.hasImage
import es.voghdev.chucknorrisjokes.app.success
import es.voghdev.chucknorrisjokes.repository.ChuckNorrisRepository

class RandomJokePresenter(val resLocator: ResLocator, val repository: ChuckNorrisRepository) :
    Presenter<RandomJokePresenter.MVPView, RandomJokePresenter.Navigator>() {

    override suspend fun initialize() {
        val task = coroutine {
            repository.getRandomJoke()
        }

        val result = task.await()
        if (result.success()) {
            view?.showJokeText(result.first?.value ?: "")
        }

        if (result.hasImage()) {
            view?.loadJokeImage(result.first?.iconUrl ?: "")
        }
    }

    interface MVPView {
        fun showJokeText(text: String)
        fun loadJokeImage(url: String)
    }

    interface Navigator {

    }
}
