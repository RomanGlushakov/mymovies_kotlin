package com.example.mymovieskotlin.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    const val BASE_URL = "https://api.kinopoisk.dev/";

    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).
    addCallAdapterFactory(RxJava3CallAdapterFactory.create()).
    addConverterFactory(GsonConverterFactory.create()).build()

    val apiService = retrofit.create(ApiService::class.java)

}