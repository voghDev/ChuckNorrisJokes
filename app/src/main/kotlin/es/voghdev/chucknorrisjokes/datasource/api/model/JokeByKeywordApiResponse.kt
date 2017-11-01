package es.voghdev.chucknorrisjokes.datasource.api.model

import es.voghdev.chucknorrisjokes.datasource.api.JokeApiEntry
import es.voghdev.chucknorrisjokes.model.Joke

class JokeByKeywordApiResponse(val total: Int = 0, val result: List<JokeApiEntry> = emptyList()) {
    fun map(): List<Joke> {
        if (total > 0)
            return result.map { it.map() }
        else
            return emptyList()
    }
}
