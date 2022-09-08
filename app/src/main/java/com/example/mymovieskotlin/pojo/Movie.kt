package com.example.mymovieskotlin.pojo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "favorite_movies")
data class Movie(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("year")
    val year: Int,

    @SerializedName("poster")
    @Embedded
    val poster: Poster,

    @SerializedName("rating")
    @Embedded
    val rating: Rating
): Serializable