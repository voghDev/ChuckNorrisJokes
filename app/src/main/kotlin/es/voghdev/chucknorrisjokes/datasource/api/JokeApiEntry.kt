package es.voghdev.chucknorrisjokes.datasource.api

import es.voghdev.chucknorrisjokes.model.Joke

class JokeApiEntry(
        val id: String = "",
        val icon_url: String = "",
        val url: String = "",
        val value: String = "",
        val category: List<String>? = emptyList()
) {
    inline fun map(): Joke = Joke(
            id = id,
            iconUrl = icon_url,
            url = url,
            value = value,
            categories = category ?: emptyList()
    )
}
