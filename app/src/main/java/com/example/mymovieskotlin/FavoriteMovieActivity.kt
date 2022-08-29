package com.example.mymovieskotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovieskotlin.adapters.MoviesAdapter
import com.example.mymovieskotlin.pojo.Movie
import kotlinx.android.synthetic.main.activity_favorite_movie.*

class FavoriteMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_movie)

        val moviesAdapter = MoviesAdapter()
        recyclerViewMovies.adapter = moviesAdapter
        recyclerViewMovies.layoutManager = GridLayoutManager(this, 2)

        val viewModel = ViewModelProvider(this)[FavoriteMoviesViewModel::class.java]

        viewModel.getFavoritesMovies().observe(this) {
            moviesAdapter.movies = it
        }

        moviesAdapter.onMovieClickListener = object : MoviesAdapter.OnMovieClickListener {
            override fun onMovieClick(movie: Movie) {
                val intent = MovieDetailActivity.newIntent(this@FavoriteMovieActivity, movie)
                startActivity(intent)
            }

        }

    }

    companion object {
         fun newIntent(context: Context): Intent {
            return Intent(context, FavoriteMovieActivity::class.java)
        }
    }

}