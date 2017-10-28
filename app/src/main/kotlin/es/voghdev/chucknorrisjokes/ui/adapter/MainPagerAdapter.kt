package es.voghdev.chucknorrisjokes.ui.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import es.voghdev.chucknorrisjokes.JokeByCategoryFragment

class MainPagerAdapter(val context: Context) : FragmentStatePagerAdapter((context as AppCompatActivity).supportFragmentManager) {
    var listener : Listener? = null

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

    override fun getItemPosition(`object`: Any?): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun getCount(): Int {
        return classes.size
    }

    interface Listener {
        fun onFragmentAdded(f: Fragment, position: Int)
    }
}
