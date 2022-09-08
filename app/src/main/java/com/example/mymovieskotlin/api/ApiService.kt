package com.example.mymovieskotlin.api

import com.example.mymovieskotlin.pojo.ActorResponse
import com.example.mymovieskotlin.pojo.MovieResponse
import com.example.mymovieskotlin.pojo.ReviewResponse
import com.example.mymovieskotlin.pojo.TrailerResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie?token=XV8WR69-PEBMFSV-PQ9MZEE-4WTETSA&field=rating.kp&search=7-10&sortField=votes.kp&sortType=-1&limit=30")
    fun loadMovies(@Query("page") page: Int): Single<MovieResponse>

    @GET("movie?token=XV8WR69-PEBMFSV-PQ9MZEE-4WTETSA&field=id")
    fun loadTrailers(@Query("search") id: Int): Single<TrailerResponse>

    @GET("review?token=XV8WR69-PEBMFSV-PQ9MZEE-4WTETSA&field=movieId")
    fun loadReviews(@Query("search") id: Int): Single<ReviewResponse>

    @GET("movie?token=XV8WR69-PEBMFSV-PQ9MZEE-4WTETSA&field=id")
    fun loadActors(@Query("search") id: Int): Single<ActorResponse>


}