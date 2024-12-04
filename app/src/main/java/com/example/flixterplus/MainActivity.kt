package com.example.flixsterplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flixsterplus.models.ActorResponse
import com.example.flixsterplus.models.TVShowResponse
import com.example.flixsterplus.models.MovieResponse
import com.example.flixsterplus.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log

class MainActivity : AppCompatActivity() {

    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var actorRecyclerView: RecyclerView
    private lateinit var actorAdapter: ActorAdapter
    private lateinit var tvShowRecyclerView: RecyclerView
    private lateinit var tvShowAdapter: TVShowAdapter

    private lateinit var movieApiService: MovieApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize RecyclerViews
        initializeRecyclerViews()

        // Initialize Retrofit
        movieApiService = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)

        // Fetch data
        fetchMovies()
        fetchActors()
        fetchTVShows()
    }

    private fun initializeRecyclerViews() {
        movieRecyclerView = findViewById(R.id.movieRecyclerView)
        movieRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        actorRecyclerView = findViewById(R.id.actorRecyclerView)
        actorRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        tvShowRecyclerView = findViewById(R.id.tvShowRecyclerView)
        tvShowRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun fetchMovies() {
        movieApiService.getPopularMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { movieResponse ->
                        movieAdapter = MovieAdapter(movieResponse.results)
                        movieRecyclerView.adapter = movieAdapter
                        Log.d("MainActivity", "Movies loaded successfully")
                    }
                } else {
                    handleApiError("Movies", response)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                handleApiFailure("Movies", t)
            }
        })
    }

    private fun fetchActors() {
        movieApiService.getPopularActors().enqueue(object : Callback<ActorResponse> {
            override fun onResponse(call: Call<ActorResponse>, response: Response<ActorResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { actorResponse ->
                        actorAdapter = ActorAdapter(actorResponse.results)
                        actorRecyclerView.adapter = actorAdapter
                        Log.d("MainActivity", "Actors loaded successfully")
                    }
                } else {
                    handleApiError("Actors", response)
                }
            }

            override fun onFailure(call: Call<ActorResponse>, t: Throwable) {
                handleApiFailure("Actors", t)
            }
        })
    }

    private fun fetchTVShows() {
        movieApiService.getPopularTVShows().enqueue(object : Callback<TVShowResponse> {
            override fun onResponse(call: Call<TVShowResponse>, response: Response<TVShowResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { tvShowResponse ->
                        tvShowAdapter = TVShowAdapter(tvShowResponse.results)
                        tvShowRecyclerView.adapter = tvShowAdapter
                        Log.d("MainActivity", "TV Shows loaded successfully")
                    }
                } else {
                    handleApiError("TV Shows", response)
                }
            }

            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                handleApiFailure("TV Shows", t)
            }
        })
    }

    private fun handleApiError(type: String, response: Response<*>) {
        Log.e("MainActivity", "Failed to load $type: ${response.errorBody()?.string()}")
        Toast.makeText(this, "Failed to load $type", Toast.LENGTH_SHORT).show()
    }

    private fun handleApiFailure(type: String, t: Throwable) {
        Log.e("MainActivity", "Error loading $type: ${t.message}")
        Toast.makeText(this, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
    }
}