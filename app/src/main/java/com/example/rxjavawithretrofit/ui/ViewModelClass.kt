package com.example.rxjavawithretrofit.ui

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjavawithretrofit.repository.Repository
import com.example.rxjavawithretrofit.response.ResponseDTO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ViewModelClass:ViewModel() {
    private val repository=Repository()
    private val mutableLiveData= MutableLiveData<MainUIModel>()
    val liveData: LiveData<MainUIModel> = mutableLiveData
    private lateinit var disposable: Disposable

    fun callApi(){
        repository.getAllCharacter().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :Observer<ResponseDTO>{
                override fun onSubscribe(d: Disposable?) {
                    d?.let {
                        disposable=it
                    }
                    //or we can also do this
                  //  disposable=d!!
                }

                override fun onNext(response: ResponseDTO?) {
                   response?.let {
                       mutableLiveData.value=MainUIModel.OnSuccess(it)
                   }
                }

                override fun onError(e: Throwable?) {
                    e?.message?.let { Log.i("ViewModel", it) }
                }

                override fun onComplete() {
                  Log.i("ViewModel","Task Completed")
                }

            })

    }
}