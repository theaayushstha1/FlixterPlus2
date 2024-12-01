package com.example.flixsterplus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.flixsterplus.models.TVShow
import android.util.Log

class TVShowAdapter(private val tvShows: List<TVShow>) : RecyclerView.Adapter<TVShowAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvShowName: TextView = view.findViewById(R.id.tvShowNameTextView)
        val tvShowImage: ImageView = view.findViewById(R.id.tvShowImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tvshow_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = tvShows[position]
        holder.tvShowName.text = tvShow.name

        val imageUrl = tvShow.posterPath?.let { "https://image.tmdb.org/t/p/w500${it.trim()}" }
        Log.d("TVShowAdapter", "Loading image URL: $imageUrl")

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .apply(RequestOptions().placeholder(R.drawable.placeholder_background).error(R.drawable.error_image_foreground))
            .into(holder.tvShowImage)
    }

    override fun getItemCount() = tvShows.size
}