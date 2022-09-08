package com.example.mymovieskotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovieskotlin.R
import com.example.mymovieskotlin.pojo.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter(): RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var onEndReachListener: OnEndReachListener? = null
    var onMovieClickListener: OnMovieClickListener? = null

    var movies: List<Movie> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.movie_item, parent, false)
        return  MovieViewHolder(view)
    }



    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
            val movie = movies[position]

            val rating = movie.rating.kpRating

        val backRound = when  {
            rating > 7 -> R.drawable.circle_green
            rating > 5 -> R.drawable.circle_orange
            else -> R.drawable.circle_red
        }

        val backRoundColor = ContextCompat.getDrawable(holder.itemView.context, backRound)

        holder.rating.background = backRoundColor
        holder.rating.text = movie.rating.kpRating.toString()


        Glide.with(holder.itemView).load(movie.poster.url).into(holder.imageViewPoster)


        holder.itemView.setOnClickListener {
            if (onMovieClickListener != null) {
                onMovieClickListener?.onMovieClick(movie)
            }
        }

        if (position > movies.size -10 && onEndReachListener != null) {
            onEndReachListener?.onReachEnd()
        }



    }

    override fun getItemCount(): Int = movies.size


    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }

    interface OnEndReachListener {
        fun onReachEnd()
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewPoster = itemView.imageViewPoster
        val rating = itemView.textViewRating
    }
}