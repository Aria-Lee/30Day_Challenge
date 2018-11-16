package com.example.fish.day19_littlebirdsoundmediaplayermediarecorder

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.file.view.*

class Adapter(val context: Context, val list: List<Data>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    interface OnItemClickListener{
        fun onClick(position: Int)
    }

    var mOnItemClickListener : OnItemClickListener? = null

    fun setOnItemClick(mOnItemClickListener: OnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.file, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.filename.text = list[position].name
        holder.timeText.text = list[position].time
        holder.itemView.setBackgroundColor(list[position].color)

        holder.itemView.setOnClickListener{
            mOnItemClickListener!!.onClick(position)
            this.notifyDataSetChanged()
            }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val filename = itemView.fileName
        val timeText = itemView.timeText
    }
}