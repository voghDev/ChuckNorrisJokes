package es.voghdev.chucknorrisjokes.app

import android.content.Context
import java.util.Collections.emptyList

class AndroidResLocator(val context: Context) : ResLocator {
    override fun getString(resId: Int): String {
        return context.getString(resId)
    }

    override fun getStringArray(resId: Int): List<String> {
        return context.resources?.getStringArray(resId)?.toList() ?: emptyList()
    }
}