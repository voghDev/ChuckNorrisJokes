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
package es.voghdev.chucknorrisjokes.ui.adapter

import android.content.Context
import android.os.Bundle
import androidx.core.app.Fragment
import androidx.core.app.FragmentStatePagerAdapter
import androidx.core.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import es.voghdev.chucknorrisjokes.ui.fragment.JokeByCategoryFragment
import es.voghdev.chucknorrisjokes.ui.fragment.JokeByKeywordFragment
import es.voghdev.chucknorrisjokes.ui.fragment.RandomJokeFragment

class MainPagerAdapter(val context: Context) : FragmentStatePagerAdapter((context as AppCompatActivity).supportFragmentManager) {
    var listener: Listener? = null

    val classes = listOf(
        RandomJokeFragment::class.java.name,
        JokeByKeywordFragment::class.java.name,
        JokeByCategoryFragment::class.java.name
    )

    override fun getItem(position: Int): Fragment {
        val args: Bundle = Bundle()
        val fragment = Fragment.instantiate(context, classes.elementAt(position), args)
        listener?.onFragmentAdded(fragment, position)
        return fragment
    }

    override fun getItemPosition(obj: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun getCount(): Int {
        return classes.size
    }

    interface Listener {
        fun onFragmentAdded(f: Fragment, position: Int)
    }
}
