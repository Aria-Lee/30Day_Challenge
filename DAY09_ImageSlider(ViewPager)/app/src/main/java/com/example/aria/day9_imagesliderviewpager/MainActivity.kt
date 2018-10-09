package com.example.aria.day9_imagesliderviewpager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val list = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addToList()
        viewPager.adapter = Adapter(this, list)
        viewPager.addOnPageChangeListener(listener)

    }

    fun addToList(){
        for( i in R.drawable.image01 .. R.drawable.image10){
            list.add(i)
        }
    }

    val listener = object: ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(p0: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            val pageNow = position + 1
            textView.setText("$pageNow / ${list.size}")
        }

        override fun onPageSelected(p0: Int) {
        }

    }



}
