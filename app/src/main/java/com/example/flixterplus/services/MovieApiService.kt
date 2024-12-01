package com.example.flixsterplus.services

import com.example.flixsterplus.models.ActorResponse
import com.example.flixsterplus.models.TVShowResponse
import com.example.flixsterplus.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("api_key") apiKey: String = "a07e22bc18f5cb106bfe4cc1f83ad8ed"): Call<MovieResponse>

    @GET("person/popular")
    fun getPopularActors(@Query("api_key") apiKey: String = "a07e22bc18f5cb106bfe4cc1f83ad8ed"): Call<ActorResponse>

    @GET("tv/popular")
    fun getPopularTVShows(@Query("api_key") apiKey: String = "a07e22bc18f5cb106bfe4cc1f83ad8ed"): Call<TVShowResponse>
}