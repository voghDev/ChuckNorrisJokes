package es.voghdev.chucknorrisjokes.repository

import es.voghdev.chucknorrisjokes.usecase.GetJokeCategories
import es.voghdev.chucknorrisjokes.usecase.GetRandomJoke
import es.voghdev.chucknorrisjokes.usecase.GetRandomJokeByCategory

class ChuckNorrisRepository(
        val getRandomJokeDataSource: GetRandomJoke,
        val getJokeCategoriesDataSource: GetJokeCategories,
        val getRandomJokeByCategoryDataSource: GetRandomJokeByCategory
) : GetRandomJoke by getRandomJokeDataSource,
        GetRandomJokeByCategory by getRandomJokeByCategoryDataSource,
        GetJokeCategories by getJokeCategoriesDataSource
