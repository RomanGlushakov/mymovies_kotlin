package com.example.mymovieskotlin.pojo

import com.google.gson.annotations.SerializedName

data class TrailerList(
    @SerializedName("trailers")
    val trailers: List<Trailer>
)