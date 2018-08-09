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

import com.google.gson.JsonSyntaxException
import es.voghdev.chucknorrisjokes.BuildConfig
import es.voghdev.chucknorrisjokes.datasource.api.model.ChuckNorrisService
import es.voghdev.chucknorrisjokes.model.AbsError
import es.voghdev.chucknorrisjokes.model.CNError
import es.voghdev.chucknorrisjokes.model.JokeCategory
import es.voghdev.chucknorrisjokes.usecase.GetJokeCategories
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetJokeCategoriesApiImpl : ApiRequest, GetJokeCategories {
    override fun getJokeCategories(): Pair<List<JokeCategory>?, AbsError?> {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG)
            builder.addInterceptor(LogJsonInterceptor())

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(getEndPoint())
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
            .build()

        val service: ChuckNorrisService = retrofit.create(ChuckNorrisService::class.java)

        val call: Call<List<String>> = service.getJokeCategories()

        try {
            val rsp: Response<List<String>>? = call.execute()

            if (rsp?.body()?.size ?: 0 > 0) {
                return Pair(rsp?.body()
                                ?.map { JokeCategory(name = it) }
                                ?: emptyList<JokeCategory>(), null)
            } else if (rsp?.errorBody() != null) {
                val error = (rsp.errorBody())?.string() ?: ""
                return Pair(null, CNError(error))
            }
        } catch (e: JsonSyntaxException) {
            return Pair(null, CNError(e.message ?: "Unknown error parsing JSON"))
        }

        return Pair(null, CNError("Unknown error"))
    }
}

