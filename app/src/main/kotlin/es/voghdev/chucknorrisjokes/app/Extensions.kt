/*
 * Copyright (C) 2018 Olmo Gallegos Hernández.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.voghdev.chucknorrisjokes.app

import android.R
import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Spinner
import arrow.core.Either
import es.voghdev.chucknorrisjokes.model.AbsError
import es.voghdev.chucknorrisjokes.model.Joke
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

fun <T> coroutine(function: () -> T): Deferred<T> {
    return async(CommonPool) { function() }
}

fun Either<AbsError, Any>.success(): Boolean {
    return this.isRight()
}

fun Either<AbsError, Any>.failure(): Boolean {
    return this.isLeft()
}

fun Either<AbsError, List<Joke>>.firstJoke(): Joke {
    return (this as? Either.Right)?.b?.elementAt(0) ?: Joke()
}

fun Either<AbsError, Joke>.hasImage(): Boolean {
    return success() && (this as? Either.Right)?.b?.iconUrl?.isNotEmpty() ?: false
}

fun Either<AbsError, List<Any>>.hasResults(): Boolean {
    return success() && (this as? Either.Right)?.b?.isNotEmpty() ?: false
}

fun Either<AbsError, List<Any>>.hasNoResults(): Boolean {
    return success() && (this as? Either.Right)?.b?.isEmpty() ?: false
}

fun <A> A.asResult() = Either.right(this)

fun Activity.ui(action: () -> Unit) {
    runOnUiThread {
        action()
    }
}

fun Fragment.ui(action: () -> Unit) {
    activity?.runOnUiThread {
        action()
    }
}

fun Spinner.configureDefaultAdapter(values: List<String>) {
    adapter = ArrayAdapter<String>(context, R.layout.simple_spinner_item, values)
}

fun Context.hideSoftKeyboard(v: View) {
    val imm: InputMethodManager? = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm?.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)
}

fun Context.showSoftKeyboard(v: View) {
    val imm: InputMethodManager? = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm?.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT)
}