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
package es.voghdev.chucknorrisjokes.datasource.api.model

import es.voghdev.chucknorrisjokes.datasource.api.JokeApiEntry
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisService {
    @GET("jokes/random")
    fun getRandomJoke(): Call<JokeApiEntry>

    @GET("jokes/categories")
    fun getJokeCategories(): Call<List<String>>

    @GET("jokes/search")
    fun getRandomJokeByKeyword(@Query("query") keyword: String): Call<JokeByKeywordApiResponse>

    @GET("jokes/random")
    fun getRandomJokeByCategory(@Query("category") category: String): Call<JokeApiEntry>
}