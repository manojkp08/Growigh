package com.example.growigh.ViewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.growigh.R

class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textTitle: TextView
    var textSource: TextView
    var imgHeadline: ImageView
    private var cardView: CardView

    init {
        textTitle = itemView.findViewById(R.id.text_title)
        textSource = itemView.findViewById(R.id.text_source)
        imgHeadline = itemView.findViewById(R.id.img_headline)
        cardView = itemView.findViewById(R.id.main_container)
    }
}
