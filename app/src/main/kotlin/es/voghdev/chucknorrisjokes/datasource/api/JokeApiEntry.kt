package es.voghdev.chucknorrisjokes.datasource.api

class JokeApiEntry(
        val id: String = "",
        val icon_url: String = "",
        val url: String = "",
        val value: String = "",
        val category: List<String>? = emptyList()
)
