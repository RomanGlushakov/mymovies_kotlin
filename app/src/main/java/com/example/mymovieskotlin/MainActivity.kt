package com.example.mymovieskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovieskotlin.adapters.MoviesAdapter
import com.example.mymovieskotlin.pojo.Movie
import kotlinx.android.synthetic.main.activity_favorite_movie.recyclerViewMovies
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private  lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val moviesAdapter = MoviesAdapter()
        recyclerViewMovies.adapter = moviesAdapter
        recyclerViewMovies.layoutManager = GridLayoutManager(this, 2)
        viewModel.movies.observe(this, Observer {
            if (it!=null) {
                moviesAdapter.movies = it
            }
        })

        viewModel.getIsLoading().observe(this) {
            if (it) {
                progressBarLoading.visibility = View.VISIBLE

            } else {
                progressBarLoading.visibility = View.GONE

            }
        }


        moviesAdapter.onEndReachListener = object : MoviesAdapter.OnEndReachListener {
            override fun onReachEnd() {
                viewModel.loadMovies()
            }

        }

        moviesAdapter.onMovieClickListener = object : MoviesAdapter.OnMovieClickListener {
            override fun onMovieClick(movie: Movie) {
                val intent = MovieDetailActivity.newIntent(this@MainActivity, movie)
                startActivity(intent)
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.favoriteMovies)
        {
            val intent = FavoriteMovieActivity.newIntent(this@MainActivity)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }



}