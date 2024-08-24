package com.example.kotlinmovieapp.serviceapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    var retrofit:Retrofit? = null
    val baseUrl = "https://api.themoviedb.org/3/"

    fun getService():MovieApiService{
        if(retrofit == null){
            retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit!!.create(MovieApiService::class.java)
    }
}