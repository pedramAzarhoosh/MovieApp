package com.example.kotlinmovieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.kotlinmovieapp.model.Movie
import com.example.kotlinmovieapp.model.MovieRepository

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    //we use android viewModel to have access of application context
    val repository = MovieRepository(application)
    public fun getPopularMovies():MutableLiveData<List<Movie>>{
        return repository.getMutableMovies()
    }

    public fun getTopRatedMovies():MutableLiveData<List<Movie>>{
        return repository.getTopRatedMovies()
    }

    public fun getUpcomingMovies():MutableLiveData<List<Movie>>{
        return repository.getUpComingMovies()
    }
}