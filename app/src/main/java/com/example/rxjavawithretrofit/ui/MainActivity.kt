package com.example.rxjavawithretrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rxjavawithretrofit.response.Result
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxjavawithretrofit.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModelClass: ViewModelClass
    private var list= ArrayList<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModelClass= ViewModelProvider(this).get(ViewModelClass::class.java)
        viewModelClass.callApi()
        viewModelClass.liveData.observe(this,{
            it.let {
                //Toast.makeText(this@MainActivity, it.info.toString(), Toast.LENGTH_SHORT).show()
                // list= it.results as ArrayList<Result>
                //setRecyclerView()
                when(it){
                    is MainUIModel.OnSuccess->{
                        list=it.responseDTO.results as ArrayList<Result>
                        setRecyclerView()
                    }
                    is MainUIModel.OnFailure->{
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
                    }

                    // for list response+
                    is MainUIModel.onSuccess ->{
                        list= it.result as ArrayList<Result>
                        setRecyclerView()
                    }
                }
            }
        })
    }

    private fun setRecyclerView() {
        val listAdapter=MainAdapter(list)
        val linearLayoutManager= LinearLayoutManager(this)
        recyclerView.apply {
            adapter=listAdapter
            layoutManager=linearLayoutManager
        }
    }

}
