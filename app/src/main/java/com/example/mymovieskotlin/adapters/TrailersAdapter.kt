package com.example.mymovieskotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieskotlin.R
import com.example.mymovieskotlin.pojo.Trailer
import kotlinx.android.synthetic.main.trailer_item.view.*
import kotlin.math.acos

class TrailersAdapter: RecyclerView.Adapter<TrailersAdapter.TrailerViewHolder>() {



    var trailers: List<Trailer> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var onTrailerClickListener: OnTrailerClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.trailer_item, parent, false)
        return TrailerViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        val trailer = trailers[position]

        holder.textViewTrailer.text = trailer.name

        holder.itemView.setOnClickListener {
            if (onTrailerClickListener != null) {
                onTrailerClickListener?.onTrailerClick(trailer)
            }
        }


    }

    override fun getItemCount(): Int = trailers.size

    interface OnTrailerClickListener {
        fun onTrailerClick(trailer: Trailer)
    }


    inner class TrailerViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val textViewTrailer = view.textViewTrailer

    }

}