package es.voghdev.chucknorrisjokes.datasource.api.model

import es.voghdev.chucknorrisjokes.datasource.api.JokeApiEntry
import retrofit2.Call
import retrofit2.http.GET

interface ChuckNorrisService {
    @GET("jokes/random")
    fun getRandomJoke(): Call<JokeApiEntry>
}