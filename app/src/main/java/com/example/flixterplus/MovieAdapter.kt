package com.example.flixsterplus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixsterplus.models.Movie
import com.example.flixterplus.R

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

        // Load the movie poster using Glide with placeholder and error images
        Glide.with(holder.itemView.context)
            .load(movie.posterUrl)
            .placeholder(R.drawable.placeholder_background) // Placeholder while loading
            .error(R.drawable.placeholder_foreground) // Error image if loading fails
            .into(holder.posterImage)
    }

    override fun getItemCount(): Int = movies.size
}