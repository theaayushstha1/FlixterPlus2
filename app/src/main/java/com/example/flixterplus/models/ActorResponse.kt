package com.example.flixsterplus.models

import com.google.gson.annotations.SerializedName

data class ActorResponse(
    @SerializedName("results") val results: List<Actor>
)

data class Actor(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("profile_path") val profilePath: String? // Use @SerializedName to map JSON field
)