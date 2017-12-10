package es.voghdev.chucknorrisjokes.usecase

import es.voghdev.chucknorrisjokes.model.AbsError
import es.voghdev.chucknorrisjokes.model.Joke

interface GetRandomJoke {
    fun getRandomJoke(): Pair<Joke?, AbsError?>
}
