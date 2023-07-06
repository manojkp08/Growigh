package com.example.growigh.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.growigh.Model.NewsHeadlines
import com.example.growigh.R
import com.example.growigh.SelectListener
import com.example.growigh.ViewHolder.CustomViewHolder
import com.squareup.picasso.Picasso

//import android.content.Context
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.growigh.Model.NewsHeadlines
//import com.example.growigh.R
//import com.example.growigh.ViewHolder.CustomViewHolder
//import com.squareup.picasso.Picasso
//
//class CustomAdapter(private val context: Context, private val headlines: List<NewsHeadlines>) :
//    RecyclerView.Adapter<CustomViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
//        val itemView = LayoutInflater.from(context).inflate(R.layout.item_facts, parent, false)
//        return CustomViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//        val currentHeadline = headlines[position]
//        holder.textTitle.text = currentHeadline.title
//        holder.textSource.text = currentHeadline.source?.name
//
//        currentHeadline.urlToImage?.let { imageUrl ->
//            Picasso.get().load(imageUrl).into(holder.imgHeadline)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return headlines.size
//    }
//}


class CustomAdapter(private val context: Context, private val headlines: List<NewsHeadlines>, private val listener: SelectListener) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_facts, parent, false)
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentHeadline = headlines[position]
        holder.textTitle.text = currentHeadline.title
        holder.textSource.text = currentHeadline.source?.name

        val imageUrl = currentHeadline.urlToImage
        if (imageUrl != null) {
            Picasso.get().load(imageUrl).into(holder.imgHeadline)
        }

        holder.itemView.setOnClickListener {
            listener.onNewsClicked(currentHeadline)
        }
    }

    override fun getItemCount(): Int {
        return headlines.size
    }
}
