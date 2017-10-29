package es.voghdev.chucknorrisjokes

import com.nhaarman.mockito_kotlin.whenever
import es.voghdev.chucknorrisjokes.app.ResLocator

fun ResLocator.hasString(id: Int, str: String) {
    whenever(getString(id)).thenReturn(str)
}