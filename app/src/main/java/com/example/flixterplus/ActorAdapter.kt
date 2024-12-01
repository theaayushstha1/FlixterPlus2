package com.example.flixsterplus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.flixsterplus.models.Actor
import android.util.Log

class ActorAdapter(private val actors: List<Actor>) : RecyclerView.Adapter<ActorAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val actorNameTextView: TextView = view.findViewById(R.id.actorNameTextView)
        val actorImageView: ImageView = view.findViewById(R.id.actorImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.actor_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actor = actors[position]
        holder.actorNameTextView.text = actor.name

        val imageUrl = actor.profilePath?.let { "https://image.tmdb.org/t/p/w500${it.trim()}" }
        Log.d("ActorAdapter", "Loading image URL: $imageUrl")

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .apply(RequestOptions().placeholder(R.drawable.placeholder_image_background).error(R.drawable.error_image_background))
            .into(holder.actorImageView)
    }

    override fun getItemCount() = actors.size
}