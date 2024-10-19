package com.example.flixsterplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flixsterplus.models.MovieResponse
import com.example.flixsterplus.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.flixterplus.R

class MainActivity : AppCompatActivity() {

    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieRecyclerView = findViewById(R.id.recyclerView)
        movieRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchMovies()
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
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load movies", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}