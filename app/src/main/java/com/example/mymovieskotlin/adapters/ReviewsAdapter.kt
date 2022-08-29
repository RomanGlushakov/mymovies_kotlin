package com.example.mymovieskotlin.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieskotlin.R
import com.example.mymovieskotlin.pojo.Review
import kotlinx.android.synthetic.main.review_item.view.*

class ReviewsAdapter: RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>() {

    var reviews: List<Review> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.review_item, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviews[position]
        holder.textViewAuthor.text = review.name
        holder.textViewReview.text = review.review

        val backGroundResId = when (review.typeReview) {
            NEGATIVE_REVIEW -> android.R.color.holo_red_light
            POSITIVE_REVIEW -> android.R.color.holo_green_light
            else -> android.R.color.holo_orange_light
        }


        val colorBackRound = ContextCompat.getColor(holder.itemView.context, backGroundResId)
        holder.linearLayoutReview.setBackgroundColor(colorBackRound)



    }

    override fun getItemCount(): Int = reviews.size



    inner class ReviewViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val textViewAuthor = view.textViewAuthor
        val textViewReview = view.textViewReview
        val linearLayoutReview = view.linearLayoutReview
    }

    companion object {
        const val NEGATIVE_REVIEW = "Негативный"
        const val POSITIVE_REVIEW = "Позитивный"
    }


}