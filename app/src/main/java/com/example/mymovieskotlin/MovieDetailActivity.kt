package com.example.mymovieskotlin

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mymovieskotlin.adapters.ActorsAdapter
import com.example.mymovieskotlin.adapters.ReviewsAdapter
import com.example.mymovieskotlin.adapters.TrailersAdapter
import com.example.mymovieskotlin.pojo.Movie
import com.example.mymovieskotlin.pojo.Trailer
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        // Получаем данные с Main Activity и устанавливаем в DetailActivity

        if (!intent.hasExtra(EXTRA_MOVIE)) {
            finish()
            return
        }
        val movie = intent.getSerializableExtra(EXTRA_MOVIE) as Movie
        textViewTitle.text = movie.name
        textViewYear.text = movie.year.toString()
        Glide.with(this).load(movie.poster.url).into(imageViewPoster)
        textViewDescription.text = movie.description


        // Установка Adapters и Recycler Views

        val actorsAdapter = ActorsAdapter()
        recyclerViewActors.adapter = actorsAdapter

        val trailersAdapter = TrailersAdapter()
        recyclerViewTrailers.adapter = trailersAdapter

        val reviewsAdapter = ReviewsAdapter()
        recyclerViewReviews.adapter = reviewsAdapter




        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]



        viewModel.loadActors(movie.id)
        viewModel.actors.observe(this) {
            if (it != null) {
                actorsAdapter.actors = it
            }
        }


        viewModel.loadTrailers(movie.id)
        viewModel.trailers.observe(this) {
            if (it != null) {
                trailersAdapter.trailers = it
            }
        }


        trailersAdapter.onTrailerClickListener = object : TrailersAdapter.OnTrailerClickListener {
            override fun onTrailerClick(trailer: Trailer) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(trailer.url)
                startActivity(intent)
            }
        }

        viewModel.loadReviews(movie.id)
        viewModel.reviews.observe(this){
            if (it!=null) {
                reviewsAdapter.reviews = it
            }
        }

        var starOff = ContextCompat.getDrawable(
            this@MovieDetailActivity,
            android.R.drawable.star_big_off
        )
        var starOn = ContextCompat.getDrawable(
            this@MovieDetailActivity,
            android.R.drawable.star_big_on
        )

        viewModel.getFavoritesMovie(movie.id).observe(this) {
            if (it == null) {
                imageViewStar.setImageDrawable(starOff)
                imageViewStar.setOnClickListener {
                    viewModel.insertMovie(movie)
                }

            }
            else {
                imageViewStar.setImageDrawable(starOn)
                imageViewStar.setOnClickListener {
                    viewModel.removeMovie(movie.id)
                }
            }
        }


    }


    companion object {
        private const val EXTRA_MOVIE = "movie"
        fun newIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE, movie)
            return intent
        }
    }
}