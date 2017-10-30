package es.voghdev.chucknorrisjokes.datasource.api.model

import es.voghdev.chucknorrisjokes.datasource.api.JokeApiEntry

class JokeByKeywordApiResponse(val total: Int = 0, val result: List<JokeApiEntry> = emptyList())
