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
import com.example.flixsterplus.R
import android.util.Log

class MainActivity : AppCompatActivity() {

    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var actorRecyclerView: RecyclerView
    private lateinit var actorAdapter: ActorAdapter
    private lateinit var tvShowRecyclerView: RecyclerView
    private lateinit var tvShowAdapter: TVShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieRecyclerView = findViewById(R.id.movieRecyclerView)
        movieRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        actorRecyclerView = findViewById(R.id.actorRecyclerView)
        actorRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        tvShowRecyclerView = findViewById(R.id.tvShowRecyclerView)
        tvShowRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        fetchMovies()
        fetchActors()
        fetchTVShows()
    }

    private fun fetchMovies() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieApiService = retrofit.create(MovieApiService::class.java)
        movieApiService.getNowPlayingMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { movieResponse ->
                        movieAdapter = MovieAdapter(movieResponse.results)
                        movieRecyclerView.adapter = movieAdapter
                        Log.d("MainActivity", "Movies loaded successfully")
                    }
                } else {
                    Log.e("MainActivity", "Failed to load movies: ${response.errorBody()?.string()}")
                    Toast.makeText(this@MainActivity, "Failed to load movies", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchActors() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieApiService = retrofit.create(MovieApiService::class.java)
        movieApiService.getPopularActors().enqueue(object : Callback<ActorResponse> {
            override fun onResponse(call: Call<ActorResponse>, response: Response<ActorResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { actorResponse ->
                        actorAdapter = ActorAdapter(actorResponse.results)
                        actorRecyclerView.adapter = actorAdapter
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load actors", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ActorResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchTVShows() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieApiService = retrofit.create(MovieApiService::class.java)
        movieApiService.getPopularTVShows().enqueue(object : Callback<TVShowResponse> {
            override fun onResponse(call: Call<TVShowResponse>, response: Response<TVShowResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { tvShowResponse ->
                        tvShowAdapter = TVShowAdapter(tvShowResponse.results)
                        tvShowRecyclerView.adapter = tvShowAdapter
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load TV shows", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}