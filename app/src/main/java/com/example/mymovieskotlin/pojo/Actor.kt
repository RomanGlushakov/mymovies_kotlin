package com.example.mymovieskotlin.pojo

import com.google.gson.annotations.SerializedName

data class Actor(
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("photo")
    val photoUrl: String? = null
)