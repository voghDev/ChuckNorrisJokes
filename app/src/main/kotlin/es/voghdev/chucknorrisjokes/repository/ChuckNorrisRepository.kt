package es.voghdev.chucknorrisjokes.repository

import es.voghdev.chucknorrisjokes.usecase.GetJokeCategories
import es.voghdev.chucknorrisjokes.usecase.GetRandomJoke
import es.voghdev.chucknorrisjokes.usecase.GetRandomJokeByCategory
import es.voghdev.chucknorrisjokes.usecase.GetRandomJokeByKeyword

class ChuckNorrisRepository(
        val getRandomJokeDataSource: GetRandomJoke,
        val getJokeCategoriesDataSource: GetJokeCategories,
        val getRandomJokeByKeywordDataSource: GetRandomJokeByKeyword,

        val getRandomJokeByCategoryDataSource: GetRandomJokeByCategory
) : GetRandomJoke by getRandomJokeDataSource,
        GetRandomJokeByCategory by getRandomJokeByCategoryDataSource,
        GetRandomJokeByKeyword by getRandomJokeByKeywordDataSource,
        GetJokeCategories by getJokeCategoriesDataSource
