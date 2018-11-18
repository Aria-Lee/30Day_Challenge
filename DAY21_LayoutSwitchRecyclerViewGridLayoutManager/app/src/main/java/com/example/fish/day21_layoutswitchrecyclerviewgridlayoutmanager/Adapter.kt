package com.example.fish.day21_layoutswitchrecyclerviewgridlayoutmanager

import android.content.Context
import android.os.Build.VERSION_CODES.P
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.cardview.view.*
import kotlinx.android.synthetic.main.cardview_detail.view.*

class Adapter(val context: Context, val list: List<Data>, var layoutManager: GridLayoutManager) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return layoutManager.spanCount
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): Adapter.ViewHolder {
        var view : View? = null
        when(type){
            1 ->{
                view = LayoutInflater.from(context).inflate(R.layout.cardview_detail, viewGroup, false)

            }
            2 -> {
                view = LayoutInflater.from(context).inflate(R.layout.cardview, viewGroup, false)
            }
        }
        return  ViewHolder(view!!, type)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(itemView: View, val viewType: Int) : RecyclerView.ViewHolder(itemView) {
        var myImage : ImageView? = null
        var myName : TextView? = null
        var year : TextView? = null
        var sex : TextView? = null

        fun bind(data: Data){
            when(viewType){
                1 -> {
                    myImage = itemView.big_imageView
                    myName = itemView.big_name_textView
                    year = itemView.year_textView
                    sex = itemView.sex_textView
                }
                2 -> {
                    myImage = itemView.small_imageView
                    myName = itemView.small_name_textView
                }

            }
            myImage?.setImageResource(data.cardImage)
            myName?.text = data.cardName
            year?.text = data.year.toString()
            sex?.text = data.sex
        }
    }
}