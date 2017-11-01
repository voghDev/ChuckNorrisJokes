package es.voghdev.chucknorrisjokes.datasource.api

import es.voghdev.chucknorrisjokes.model.AbsError
import es.voghdev.chucknorrisjokes.model.Joke
import es.voghdev.chucknorrisjokes.usecase.GetRandomJokeByKeyword

class GetRandomJokeByKeywordApiImpl : GetRandomJokeByKeyword {
    override fun getRandomJokeByKeyword(keyword: String): Pair<List<Joke>?, AbsError?> {
        return Pair(null, null) // TODO implement
    }
}
