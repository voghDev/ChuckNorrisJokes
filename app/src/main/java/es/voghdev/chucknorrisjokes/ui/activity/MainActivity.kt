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
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import es.voghdev.chucknorrisjokes.R
import es.voghdev.chucknorrisjokes.app.AndroidResLocator
import es.voghdev.chucknorrisjokes.app.ui
import es.voghdev.chucknorrisjokes.databinding.ActivityMainBinding
import es.voghdev.chucknorrisjokes.ui.adapter.MainPagerAdapter
import es.voghdev.chucknorrisjokes.ui.presenter.MainPresenter

class MainActivity : BaseActivity(), MainPresenter.MVPView, MainPresenter.Navigator {
    var presenter: MainPresenter? = null
    lateinit var onTabSelectedListener: TabLayout.OnTabSelectedListener
    lateinit var adapter: MainPagerAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        presenter = MainPresenter(AndroidResLocator(this))
        presenter?.view = this
        presenter?.navigator = this

        onTabSelectedListener = TabLayout.ViewPagerOnTabSelectedListener(binding.viewPager)

        presenter?.initialize()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter?.destroy()

        binding.tabLayout.removeOnTabSelectedListener(onTabSelectedListener)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun configureTabs() = ui {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Random"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("By Keyword"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("By Category"))

        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        binding.tabLayout.addOnTabSelectedListener(onTabSelectedListener)
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
            }
        })

        adapter = MainPagerAdapter(this)
        binding.viewPager.adapter = adapter
    }
}
