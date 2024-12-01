package com.example.flixsterplus.models

import com.google.gson.annotations.SerializedName

data class TVShowResponse(
    @SerializedName("results") val results: List<TVShow>
)

data class TVShow(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String? // Maps to 'poster_path' from the API
)