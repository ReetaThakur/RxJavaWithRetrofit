package com.example.rxjavawithretrofit.ui

import com.example.rxjavawithretrofit.response.ResponseDTO
import com.example.rxjavawithretrofit.response.Result

sealed class MainUIModel {
    data class OnSuccess(val responseDTO: ResponseDTO):MainUIModel()

    data class OnFailure(val error:String):MainUIModel()

    //for list
    data class onSuccess(val result: List<Result>):MainUIModel()
}