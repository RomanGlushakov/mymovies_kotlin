package com.example.mymovieskotlin.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mymovieskotlin.pojo.Movie
import io.reactivex.rxjava3.core.Completable

@Dao
interface MovieDao {

    @Query("SELECT * FROM favorite_movies")
    fun getAllFavoriteMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM favorite_movies WHERE id=:movieId")
    fun getFavoriteMovie(movieId: Int): LiveData<Movie>

    @Insert
    fun insertMovie(movie: Movie): Completable

    @Query("DELETE FROM favorite_movies WHERE id=:movieId")
    fun removeMovie(movieId: Int): Completable

}