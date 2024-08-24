package com.example.kotlinmovieapp.model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.kotlinmovieapp.R
import com.example.kotlinmovieapp.serviceapi.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MovieRepository {
    private val movies = arrayListOf<Movie>()
    private val mutableLiveData = MutableLiveData<List<Movie>>()
    lateinit var application:Application //in order to access the resources

    constructor(application: Application) {
        this.application = application
    }

    public fun getMutableMovies():MutableLiveData<List<Movie>>{
        val movieApiService = RetrofitInstance.getService()

        val call: Call<MovieResult> = movieApiService.getPopularMovies(application.applicationContext.getString(
            R.string.api_key))

        // you can use enqueue or execute for having http request but enqueue is better because handle the request in background
        // and the result in the ui thread

        call.enqueue(object : Callback<MovieResult> {
            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                val result = response.body()
                if (result?.results != null) {
                    val movies = result.results as ArrayList<Movie>
                    mutableLiveData.value = movies
                }
            }

            override fun onFailure(call: Call<MovieResult>, throwable: Throwable) {
                // Handle failure here
            }
        })

        return mutableLiveData
    }

}