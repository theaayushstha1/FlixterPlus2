package com.example.flixsterplus

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.flixsterplus.R

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val tvShowTitle = intent.getStringExtra("TV_SHOW_TITLE")
        val tvShowOverview = intent.getStringExtra("TV_SHOW_OVERVIEW")
        val tvShowPoster = intent.getStringExtra("TV_SHOW_POSTER")

        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val overviewTextView: TextView = findViewById(R.id.overviewTextView)
        val posterImageView: ImageView = findViewById(R.id.posterImageView)

        titleTextView.text = tvShowTitle
        overviewTextView.text = tvShowOverview

        Glide.with(this)
            .load(tvShowPoster)
            .placeholder(R.drawable.placeholder_background)
            .error(R.drawable.error_image_foreground)
            .into(posterImageView)
    }
}