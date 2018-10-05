package com.example.aria.day6_imagelistrecyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.util.zip.Inflater
import kotlinx.android.synthetic.main.image.view.*

class Adapter(val contentList: List<content>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): Adapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.image, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = this.contentList.count()


    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        holder.bind(contentList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView = itemView.imageView
        val textView = itemView.textView

        fun bind(content: content) {
            imageView.setImageResource(content.image)
            textView.setText(content.text)
            Log.wtf("aaaaa", content.image.toString())
        }

    }
}