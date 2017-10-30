package es.voghdev.chucknorrisjokes

import com.nhaarman.mockito_kotlin.whenever
import es.voghdev.chucknorrisjokes.app.ResLocator
import org.mockito.Mockito

fun ResLocator.hasString(id: Int, str: String) {
    whenever(getString(id)).thenReturn(str)
}

fun <T> anyCategory(): T {
    return Mockito.anyObject<T>()
}

fun <T> anyJoke(): T {
    return Mockito.anyObject<T>()
}