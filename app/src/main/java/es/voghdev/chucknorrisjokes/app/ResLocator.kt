package es.voghdev.chucknorrisjokes.app

import android.support.annotation.StringRes

interface ResLocator {
    fun getString(@StringRes resId: Int): String
    fun getStringArray(@StringRes resId: Int): List<String>
}
