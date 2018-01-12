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