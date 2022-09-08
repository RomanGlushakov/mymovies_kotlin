package com.example.mymovieskotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovieskotlin.database.MovieDataBase
import com.example.mymovieskotlin.pojo.Movie

class FavoriteMoviesViewModel(application: Application): AndroidViewModel(application) {


    var movieDao = MovieDataBase.getInstance(application).movieDao()

    public fun getFavoritesMovies(): LiveData<List<Movie>> {
        return movieDao.getAllFavoriteMovies()
    }







}