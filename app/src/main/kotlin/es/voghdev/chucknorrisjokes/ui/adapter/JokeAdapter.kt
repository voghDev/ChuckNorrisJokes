/*
 * Copyright (C) 2017 Olmo Gallegos Hernández.
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
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import es.voghdev.chucknorrisjokes.R
import es.voghdev.chucknorrisjokes.model.Joke
import es.voghdev.chucknorrisjokes.ui.viewholder.JokeViewHolder

class JokeAdapter(val context: Context) : RecyclerView.Adapter<JokeViewHolder>() {
    val jokes: MutableList<Joke> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_joke, parent, false)
        return JokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: JokeViewHolder?, position: Int) {
        val joke = jokes.elementAt(position)

        holder?.tv_text?.text = joke.value

        Picasso.with(context)
                .load(joke.iconUrl)
                .into(holder?.iv_image)
    }

    override fun getItemCount(): Int {
        return jokes.size
    }

    fun add(joke: Joke) {
        jokes.add(joke)
    }
}
