package es.voghdev.chucknorrisjokes.usecase

import es.voghdev.chucknorrisjokes.model.AbsError
import es.voghdev.chucknorrisjokes.model.JokeCategory

interface GetJokeCategories {
    fun getJokeCategories() : Pair<List<JokeCategory>?, AbsError?>
}
