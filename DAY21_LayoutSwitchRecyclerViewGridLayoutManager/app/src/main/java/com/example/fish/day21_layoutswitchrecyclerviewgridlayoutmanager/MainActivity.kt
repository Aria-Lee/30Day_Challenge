package com.example.fish.day21_layoutswitchrecyclerviewgridlayoutmanager

import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var adapter: Adapter
    lateinit var layoutManager: GridLayoutManager
    var myList = mutableListOf<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
    }

    fun initRecyclerView(){
        myList = SetData().setList(this)
        layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        adapter = Adapter(this, myList, layoutManager)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.switch_icon){
            switch(item)
        }
        return true
    }

    fun switch(item: MenuItem?){
        when(layoutManager.spanCount){
            1 -> {
                layoutManager.spanCount = 2
                item?.icon = ContextCompat.getDrawable(this, R.drawable.ic_big_item)
            }
            2 -> {
                layoutManager.spanCount = 1
                item?.icon = ContextCompat.getDrawable(this, R.drawable.ic_small_item)
           }

        }
    }

}
