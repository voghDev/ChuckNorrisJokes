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
package es.voghdev.chucknorrisjokes.datasource.api

import es.voghdev.chucknorrisjokes.model.Joke

class JokeApiEntry(
    val id: String = "",
    val icon_url: String = "",
    val url: String = "",
    val value: String = "",
    val category: List<String>? = emptyList()
) {
    inline fun map(): Joke = Joke(
        id = id,
        iconUrl = icon_url,
        url = url,
        value = value,
        categories = category ?: emptyList()
    )
}
