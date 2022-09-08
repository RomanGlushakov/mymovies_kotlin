package com.example.mymovieskotlin.pojo

import com.google.gson.annotations.SerializedName

data class ActorResponse(
    @SerializedName("persons")
    val persons: List<Actor>
)