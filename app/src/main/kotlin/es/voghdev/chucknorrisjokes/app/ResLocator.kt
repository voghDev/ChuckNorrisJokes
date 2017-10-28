package es.voghdev.chucknorrisjokes.app

interface ResLocator {
    fun getString(  resId: Int): String
    fun getStringArray(resId: Int): List<String>
}
