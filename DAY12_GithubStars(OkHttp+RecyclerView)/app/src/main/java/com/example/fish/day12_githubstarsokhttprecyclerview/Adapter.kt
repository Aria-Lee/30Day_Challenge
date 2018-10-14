package com.example.fish.day12_githubstarsokhttprecyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fish.day12_githubstarsokhttprecyclerview.Data.ReposData
import kotlinx.android.synthetic.main.item.view.*

class Adapter(val context: Context, val reposList: ArrayList<ReposData>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reposList.size
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        holder.bind(reposList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repoName = itemView.repoName
        val starNum = itemView.star_num
        val forkNum = itemView.fork_num
        val watchNum = itemView.watch_num
        val codeLanguage = itemView.codeLanguage
        val creatTime = itemView.creatTime
        val updateTime = itemView.updateTime

        @SuppressLint("SetTextI18n")
        fun bind(item: ReposData) {
            repoName.setText(item.repoName)
            starNum.setText(item.stargazers_count)
            forkNum.setText(item.forks_count)
            watchNum.setText(item.watchers_count)
            codeLanguage.setText(item.language)
            creatTime.setText("Creat at    "+item.creatTIme)
            updateTime.setText("Update at "+item.updateTime)
        }
    }
}