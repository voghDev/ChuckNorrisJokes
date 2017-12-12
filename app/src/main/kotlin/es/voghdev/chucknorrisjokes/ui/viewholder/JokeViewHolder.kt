package es.voghdev.chucknorrisjokes.ui.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.row_joke.view.*

class JokeViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
    val iv_image: ImageView
    val tv_text: TextView

    init {
        iv_image = rootView.iv_image
        tv_text = rootView.tv_text
    }
}
