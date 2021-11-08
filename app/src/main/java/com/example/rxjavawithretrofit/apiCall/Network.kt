package com.example.rxjavawithretrofit.apiCall

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    fun getRetrofit()=Retrofit.Builder().baseUrl("https://rickandmortyapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    fun getApiService()= getRetrofit().create(ApiService::class.java)
}