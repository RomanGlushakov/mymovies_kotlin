package com.example.mymovieskotlin.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rating(
    @SerializedName("kp")
    val kpRating: Double
) : Serializable