package es.voghdev.chucknorrisjokes.datasource.api.model

import es.voghdev.chucknorrisjokes.datasource.api.JokeApiEntry
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ChuckNorrisService {
    @FormUrlEncoded
    @POST("jokes/random")
    fun getRandomJoke(): Call<JokeApiEntry>
}