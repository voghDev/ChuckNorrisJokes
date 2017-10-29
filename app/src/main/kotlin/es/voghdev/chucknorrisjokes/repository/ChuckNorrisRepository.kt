package es.voghdev.chucknorrisjokes.repository

import es.voghdev.chucknorrisjokes.usecase.GetRandomJoke

class ChuckNorrisRepository(
        val getRandomJokeDataSource: GetRandomJoke
) : GetRandomJoke by getRandomJokeDataSource