package es.voghdev.chucknorrisjokes

import org.mockito.Mockito

fun <T> anyCategory(): T {
    return Mockito.anyObject<T>()
}

fun <T> anyJoke(): T {
    return Mockito.anyObject<T>()
}