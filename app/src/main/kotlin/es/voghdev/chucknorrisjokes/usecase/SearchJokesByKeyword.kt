package es.voghdev.chucknorrisjokes.usecase

import es.voghdev.chucknorrisjokes.model.AbsError
import es.voghdev.chucknorrisjokes.model.Joke

interface SearchJokesByKeyword {
    fun searchJokesByKeyword(keyword: String): Pair<List<Joke>?, AbsError?>
}
