package com.example.rxjavawithretrofit.repository

import com.example.rxjavawithretrofit.apiCall.Network
import com.example.rxjavawithretrofit.response.ResponseDTO
import io.reactivex.rxjava3.core.Observable

class Repository {

   fun getAllCharacter() : Observable<ResponseDTO>{
        return Network.getApiService().getInstance()
   }
}