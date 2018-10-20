package com.example.fish.day17_pulltorequestswiperefreshlayoutcardlayout

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.cardview.view.*

class Adapter(val context: Context, val list: List<Data>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): Adapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        holder.myImage.setImageResource(list[position].cardImage)
        holder.myName.setText(list[position].cardName)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val myImage = itemView.imageView
        val myName = itemView.textView
    }
}