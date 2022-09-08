package com.example.mymovieskotlin.pojo

import com.google.gson.annotations.SerializedName

data class Trailer(
    @SerializedName("url")
     val url: String? = null,
    @SerializedName("name")
     val name: String? = null
)