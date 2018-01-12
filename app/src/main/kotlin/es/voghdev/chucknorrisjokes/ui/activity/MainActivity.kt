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
package es.voghdev.chucknorrisjokes.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import es.voghdev.chucknorrisjokes.R
import es.voghdev.chucknorrisjokes.app.AndroidResLocator
import es.voghdev.chucknorrisjokes.ui.adapter.MainPagerAdapter
import es.voghdev.chucknorrisjokes.ui.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.runBlocking

class MainActivity : BaseActivity(), MainPresenter.MVPView, MainPresenter.Navigator {
    var presenter: MainPresenter? = null
    lateinit var onTabSelectedListener: TabLayout.OnTabSelectedListener
    lateinit var adapter: MainPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = MainPresenter(AndroidResLocator(this))
        presenter?.view = this
        presenter?.navigator = this

        onTabSelectedListener = TabLayout.ViewPagerOnTabSelectedListener(viewPager)

        runBlocking {
            presenter?.initialize()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        runBlocking {
            presenter?.destroy()
        }

        tabLayout.removeOnTabSelectedListener(onTabSelectedListener)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun configureTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("Random"))
        tabLayout.addTab(tabLayout.newTab().setText("By Keyword"))
        tabLayout.addTab(tabLayout.newTab().setText("By Category"))

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(onTabSelectedListener)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
            }
        })

        adapter = MainPagerAdapter(this)
        viewPager.adapter = adapter
    }
}
