package com.example.mymovieskotlin

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovieskotlin.api.ApiFactory
import com.example.mymovieskotlin.database.MovieDataBase
import com.example.mymovieskotlin.pojo.Actor
import com.example.mymovieskotlin.pojo.Movie
import com.example.mymovieskotlin.pojo.Review
import com.example.mymovieskotlin.pojo.Trailer
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailViewModel(application: Application): AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    var actors = MutableLiveData<List<Actor>>()
    var trailers = MutableLiveData<List<Trailer>>()
    var reviews = MutableLiveData<List<Review>>()
    var movieDao = MovieDataBase.getInstance(application).movieDao()

    public fun getFavoritesMovie(movieId: Int): LiveData<Movie> {
        return movieDao.getFavoriteMovie(movieId)
    }


    fun loadActors(id: Int) {

        val disposable = ApiFactory.apiService.loadActors(id).
        subscribeOn(Schedulers.io()).
        observeOn(AndroidSchedulers.mainThread()).
        subscribe({
                  if (it != null) {
                      actors.value = it.persons
                  }
        }, {
            Log.d("MovieDetailViewModel", it.message.toString())
        })

        compositeDisposable.add(disposable)
    }


    fun loadTrailers (id: Int) {
        val disposable = ApiFactory.apiService.loadTrailers(id).
        subscribeOn(Schedulers.io()).
        observeOn(AndroidSchedulers.mainThread()).
        subscribe({
                  trailers.value = it.trailersList.trailers
        }, {
            Log.d("MovieDetailViewModel", it.message.toString())
        })
        compositeDisposable.add(disposable)
    }

    fun loadReviews(id: Int) {
        val disposable = ApiFactory.apiService.loadReviews(id).
        subscribeOn(Schedulers.io()).
        observeOn(AndroidSchedulers.mainThread()).
        subscribe({
              reviews.value = it.reviews
        }, {
            Log.d("MovieDetailViewModel", it.message.toString())
        })
        compositeDisposable.add(disposable)
    }

    fun removeMovie(movieId: Int) {
        val disposable = movieDao.removeMovie(movieId).
        subscribeOn(Schedulers.io()).
        observeOn(AndroidSchedulers.mainThread()).
        subscribe({

        }, {
            Log.d("MovieDetailViewModel", it.message.toString())
        })
        compositeDisposable.add(disposable)
    }

    fun insertMovie(movie: Movie) {
        val disposable = movieDao.insertMovie(movie).
        subscribeOn(Schedulers.io()).
        observeOn(AndroidSchedulers.mainThread()).
        subscribe({

        }, {
            Log.d("MovieDetailViewModel", it.message.toString())
        })
        compositeDisposable.add(disposable)
    }





    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }



}