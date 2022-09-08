package com.example.mymovieskotlin

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovieskotlin.api.ApiFactory
import com.example.mymovieskotlin.pojo.Movie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel (application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    private var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private var page = 1

    var movies = MutableLiveData<List<Movie>>()

    init {
        loadMovies()
    }

    fun getIsLoading(): LiveData<Boolean> {
        return isLoading
    }

    fun loadMovies() {
        val loading = isLoading.value
        if (loading != null && loading) {
            return
        }
        val disposable = ApiFactory.apiService.loadMovies(page).
        subscribeOn(Schedulers.io()).
        observeOn(AndroidSchedulers.mainThread()).
            doOnSubscribe {
                isLoading.value = true
            }.
            doAfterTerminate {
                isLoading.value = false

            }.
        subscribe({
            val loadedMovies = movies.value?.toMutableList()
            if (loadedMovies != null && it.movies !=null ) {
                loadedMovies.addAll(it.movies)
                movies.value = loadedMovies.toMutableList()

            } else {
                movies.value = it.movies
                Log.d("MainViewModel", "Блок первого добавления фильмов")
                Log.d("MainViewModel", it.toString())
            }
            page++



        }, {
                Log.d("MainViewModel", it.message.toString())
        })
        compositeDisposable.add(disposable)
    }




    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }





}