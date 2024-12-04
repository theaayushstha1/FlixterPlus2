package com.example.flixsterplus

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.flixsterplus.models.TVShow

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

        // Load the TV show image using Glide
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholder_background)
                    .error(R.drawable.error_image_foreground)
            )
            .into(holder.tvShowImage)

        // Add click listener for each item
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailsActivity::class.java).apply {
                putExtra("TV_SHOW_NAME", tvShow.name)
                putExtra("TV_SHOW_OVERVIEW", tvShow.overview)
                putExtra("TV_SHOW_POSTER", imageUrl)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = tvShows.size
}