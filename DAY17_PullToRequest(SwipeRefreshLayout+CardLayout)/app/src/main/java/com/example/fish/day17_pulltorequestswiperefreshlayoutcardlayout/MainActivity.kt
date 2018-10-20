package com.example.fish.day17_pulltorequestswiperefreshlayoutcardlayout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var adapter: Adapter
    var myList = mutableListOf<Data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myList = SetData().setList(this)
        adapter = Adapter(this, myList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        swipe.setOnRefreshListener(listener)
    }

    val listener = SwipeRefreshLayout.OnRefreshListener {
        myList.shuffle()
        adapter.notifyDataSetChanged()
        swipe.isRefreshing = false
    }
}
