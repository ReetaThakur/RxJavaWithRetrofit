package com.example.rxjavawithretrofit.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rxjavawithretrofit.response.Result
import com.example.rxjavawithretrofit.R
import org.w3c.dom.Text

class MainAdapter(private val charcterList:List<Result>):RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view:View=LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resultDTO= charcterList[position]
        holder.setData(resultDTO)
    }

    override fun getItemCount(): Int {
        return charcterList.size
    }


    class ViewHolder(val itemView:View):RecyclerView.ViewHolder(itemView){
        var image:ImageView=itemView.findViewById(R.id.ivCharacter)
        var name:TextView=itemView.findViewById(R.id.tv_name)
        fun setData(result:Result){
            Glide.with(image).load(result.image).into(image)
            name.text=result.name
        }

    }

}