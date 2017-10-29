package es.voghdev.chucknorrisjokes.datasource.api

interface ApiRequest {
    fun getEndPoint(): String {
        return "http://api.chucknorris.io/"
    }
}
