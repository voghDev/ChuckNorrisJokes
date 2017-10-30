package es.voghdev.chucknorrisjokes.usecase

import es.voghdev.chucknorrisjokes.model.AbsError
import es.voghdev.chucknorrisjokes.model.Joke
import es.voghdev.chucknorrisjokes.model.JokeCategory

interface GetRandomJokeByCategory {
    fun getRandomJokeByCategory(category: JokeCategory): Pair<Joke?, AbsError?>
}
