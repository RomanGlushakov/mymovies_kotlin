package com.example.mymovieskotlin.pojo

import com.google.gson.annotations.SerializedName

data class TrailerResponse(
    @SerializedName("videos")
    val trailersList: TrailerList
)