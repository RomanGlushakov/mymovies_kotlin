package com.example.mymovieskotlin.pojo

import com.google.gson.annotations.SerializedName

data class ReviewResponse (
    @SerializedName("docs")
    val reviews: List<Review>
        )