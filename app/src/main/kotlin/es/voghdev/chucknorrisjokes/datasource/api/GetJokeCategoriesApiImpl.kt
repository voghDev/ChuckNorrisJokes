package es.voghdev.chucknorrisjokes.datasource.api

import es.voghdev.chucknorrisjokes.model.AbsError
import es.voghdev.chucknorrisjokes.model.CNError
import es.voghdev.chucknorrisjokes.model.JokeCategory
import es.voghdev.chucknorrisjokes.usecase.GetJokeCategories

class GetJokeCategoriesApiImpl : GetJokeCategories {
    override fun getJokeCategories(): Pair<List<JokeCategory>?, AbsError?> {
        return Pair(null, CNError("Not implemented"))
    }
}
