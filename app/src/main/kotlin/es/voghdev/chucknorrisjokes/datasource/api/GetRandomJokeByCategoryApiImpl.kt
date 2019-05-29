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

import arrow.core.Either
import com.google.gson.JsonSyntaxException
import es.voghdev.chucknorrisjokes.BuildConfig
import es.voghdev.chucknorrisjokes.datasource.api.model.ChuckNorrisService
import es.voghdev.chucknorrisjokes.model.AbsError
import es.voghdev.chucknorrisjokes.model.CNError
import es.voghdev.chucknorrisjokes.model.Joke
import es.voghdev.chucknorrisjokes.model.JokeCategory
import es.voghdev.chucknorrisjokes.usecase.GetRandomJokeByCategory
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetRandomJokeByCategoryApiImpl : GetRandomJokeByCategory, ApiRequest {
    override fun getRandomJokeByCategory(category: JokeCategory): Either<AbsError, Joke> {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG)
            builder.addInterceptor(LogJsonInterceptor())

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(getEndPoint())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build()

        val service: ChuckNorrisService = retrofit.create(ChuckNorrisService::class.java)

        val call: Call<JokeApiEntry> = service.getRandomJokeByCategory(category.name)

        try {
            val rsp: Response<JokeApiEntry>? = call.execute()

            if (rsp?.body() ?: Joke() is JokeApiEntry) {
                return Either.right(rsp?.body()?.map() ?: Joke())
            } else if (rsp?.errorBody() != null) {
                val error = (rsp.errorBody())?.string() ?: ""
                return Either.left(CNError(error))
            }

            return Either.left(CNError("Unknown error"))
        } catch (e: JsonSyntaxException) {
            return Either.left(CNError(e.message ?: "Unknown error parsing JSON"))
        }
    }
}
