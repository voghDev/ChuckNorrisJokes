package es.voghdev.chucknorrisjokes

import com.nhaarman.mockitokotlin2.any
import es.voghdev.chucknorrisjokes.model.Joke
import es.voghdev.chucknorrisjokes.model.JokeCategory

fun anyCategory(): JokeCategory = any()

fun anyJoke(): Joke = any()