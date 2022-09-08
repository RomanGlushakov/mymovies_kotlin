package com.example.mymovieskotlin.pojo

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("author")
    val name: String,
    @SerializedName("type")
    val typeReview: String,
    @SerializedName("review")
    val review: String
)