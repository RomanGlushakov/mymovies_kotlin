package com.example.mymovieskotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mymovieskotlin.pojo.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDataBase : RoomDatabase() {

    companion object {
        private const val MOVIE_BD = "movie.db"
        private var db: MovieDataBase? = null
        private val LOCK = Any()


        fun getInstance(context: Context): MovieDataBase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(context, MovieDataBase::class.java, MOVIE_BD).build()
                db = instance
                return instance
            }
        }
    }

    abstract fun movieDao(): MovieDao

}