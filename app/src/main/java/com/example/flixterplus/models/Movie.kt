package com.example.flixsterplus.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String
) {
    val posterUrl: String
        get() = "https://image.tmdb.org/t/p/w500$posterPath"
}