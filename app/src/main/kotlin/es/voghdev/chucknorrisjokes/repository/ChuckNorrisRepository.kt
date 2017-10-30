package es.voghdev.chucknorrisjokes.repository

import es.voghdev.chucknorrisjokes.usecase.GetJokeCategories
import es.voghdev.chucknorrisjokes.usecase.GetRandomJoke

class ChuckNorrisRepository(val getRandomJokeDataSource: GetRandomJoke, val getJokeCategoriesDataSource: GetJokeCategories)
    : GetRandomJoke by getRandomJokeDataSource,
        GetJokeCategories by getJokeCategoriesDataSource
