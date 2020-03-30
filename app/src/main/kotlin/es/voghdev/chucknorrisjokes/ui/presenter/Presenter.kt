/*
 * Copyright (C) 2017 Olmo Gallegos Hernández.
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

import kotlinx.coroutines.*

abstract class Presenter<T1, T2>() {
    fun initializeAsync() {
        scope.launch {
            initialize()
        }
    }

    open suspend fun initialize() { /* Empty */

    }

    fun resumeAsync() {
        scope.launch {
            resume()
        }
    }

    open fun resume() { /* Empty */
    }

    open fun pause() { /* Empty */
    }

    open fun destroy() { /* Empty */
    }

    var view: T1? = null
    var navigator: T2? = null
    val scope = PresenterScope()
}

class PresenterScope : CoroutineScope by MainScope() {
    val job = Job()
    override val coroutineContext = Dispatchers.Main + job
}
