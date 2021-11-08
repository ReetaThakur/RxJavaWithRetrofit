package com.example.rxjavawithretrofit.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjavawithretrofit.repository.Repository
import com.example.rxjavawithretrofit.response.ResponseDTO
import com.example.rxjavawithretrofit.response.Result
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class PracticeViewModel: ViewModel(){

    private val repository= Repository()
    private var resltList= arrayListOf<Result>()
    private val mutableLiveData= MutableLiveData<MainUIModel>()
    val liveData: LiveData<MainUIModel> = mutableLiveData
    private lateinit var disposable: Disposable

    fun callApi(){
       repository.getAllCharacter()
           .flatMap { (_, results) ->
               Observable.fromIterable(results)
           }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
           .subscribe(object :Observer<Result>{
               override fun onSubscribe(d: Disposable?) {
                   disposable=d!!
               }

               override fun onNext(resultDto: Result?) {
                  resultDto?.let {
                      resltList.add(it)
                  }
               }

               override fun onError(e: Throwable?) {
                   e?.message?.let { Log.i("ViewModel", it) }
               }

               override fun onComplete() {
                  mutableLiveData.value=MainUIModel.onSuccess(resltList)
               }

           })
    }
}