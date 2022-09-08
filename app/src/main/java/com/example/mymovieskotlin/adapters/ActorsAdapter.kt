package com.example.mymovieskotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovieskotlin.R
import com.example.mymovieskotlin.pojo.Actor
import com.example.mymovieskotlin.pojo.Trailer
import kotlinx.android.synthetic.main.actor_item.view.*
import kotlinx.android.synthetic.main.trailer_item.view.*

class ActorsAdapter: RecyclerView.Adapter<ActorsAdapter.ActorsViewHolder>() {

    var actors: List<Actor> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {

        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.actor_item, parent, false)
        return ActorsViewHolder(view)

    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        val actor = actors[position]

        Glide.with(holder.itemView).load(actor.photoUrl).into(holder.imageViewActor)
        holder.textViewActorName.text = actor.name



    }

    override fun getItemCount(): Int = actors.size





    inner class ActorsViewHolder(view: View): RecyclerView.ViewHolder (view) {
        val imageViewActor =  view.imageViewActor
        val textViewActorName = view.textViewActorName
    }


}