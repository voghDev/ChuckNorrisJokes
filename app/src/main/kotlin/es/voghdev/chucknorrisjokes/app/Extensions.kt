package es.voghdev.chucknorrisjokes.app

import android.R
import android.widget.ArrayAdapter
import android.widget.Spinner
import es.voghdev.chucknorrisjokes.model.AbsError
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

fun <T> coroutine(function: () -> T): Deferred<T> {
    return async(CommonPool) { function() }
}

fun Pair<Any?, AbsError?>.success(): Boolean {
    return first != null
}

fun Pair<Any?, AbsError?>.failure(): Boolean {
    return second != null
}

fun Spinner.configureDefaultAdapter(values: List<String>) {
    adapter = ArrayAdapter<String>(context, R.layout.simple_spinner_item, values)
}