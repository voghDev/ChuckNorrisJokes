package es.voghdev.chucknorrisjokes.datasource.mock

import es.voghdev.chucknorrisjokes.model.AbsError
import es.voghdev.chucknorrisjokes.model.CNError
import es.voghdev.chucknorrisjokes.model.Joke
import es.voghdev.chucknorrisjokes.usecase.GetRandomJoke

class GetRandomJokeMockImpl : GetRandomJoke {
    override fun getRandomJoke(): Pair<Joke?, AbsError?> {
        return Pair(null, CNError("Not implemented"))
    }
}
