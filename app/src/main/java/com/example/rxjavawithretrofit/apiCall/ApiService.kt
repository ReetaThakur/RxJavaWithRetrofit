package com.example.rxjavawithretrofit.apiCall

import com.example.rxjavawithretrofit.response.ResponseDTO
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("api/character")
     fun getInstance():Observable<ResponseDTO>
}