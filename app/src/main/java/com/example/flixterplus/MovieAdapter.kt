package com.example.flixsterplus

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.flixsterplus.models.Movie

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.movieTitle)
        val overview: TextView = itemView.findViewById(R.id.movieOverview)
        val posterImage: ImageView = itemView.findViewById(R.id.moviePoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.title
        holder.overview.text = movie.overview

        // Check and log the URL before loading
        Log.d("MovieAdapter", "Poster URL: ${movie.posterUrl}")

        // Load the movie poster using Glide with enhancements
        Glide.with(holder.itemView.context)
            .load(movie.posterUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholder_background)  // Placeholder while loading
                    .error(R.drawable.error_image_foreground)         // Error image if loading fails
                    .diskCacheStrategy(DiskCacheStrategy.ALL)         // Cache both the full-size image and the resized one
                    .centerCrop()                                     // Apply center crop to maintain aspect ratio and fill the space
            )
            .into(holder.posterImage)
    }

    override fun getItemCount(): Int = movies.size
}