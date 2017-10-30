package es.voghdev.chucknorrisjokes.datasource.api.model

import es.voghdev.chucknorrisjokes.datasource.api.JokeApiEntry
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface ChuckNorrisService {
    @FormUrlEncoded
    @POST("jokes/random")
    fun getRandomJoke(): Call<JokeApiEntry>

    @FormUrlEncoded
    @POST("jokes/categories")
    fun getJokeCategories(): Call<List<String>>

    @FormUrlEncoded
    @POST("jokes/search")
    fun getRandomJokeByKeyword(@Query("query") keyword: String): Call<JokeByKeywordApiResponse>

    @FormUrlEncoded
    @POST("jokes/random")
    fun getRandomJokeByCategory(@Query("category") category: String): Call<JokeApiEntry>
}