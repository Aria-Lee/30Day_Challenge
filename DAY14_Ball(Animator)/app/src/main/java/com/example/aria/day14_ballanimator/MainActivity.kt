package com.example.aria.day14_ballanimator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        value_ani.setOnClickListener(listener)
        object_ani.setOnClickListener(listener)
        set.setOnClickListener(listener)
        interpolator.setOnClickListener(listener)
    }

    val listener = View.OnClickListener{
        val intent = when(it){
            value_ani ->  Intent(this, ValueAnimatorActivity::class.java)
            object_ani -> Intent(this, ObjectAnimatorActivity::class.java)
            set -> Intent(this, AnimatorSetActivity::class.java)
            else -> Intent(this, InterpolatorActivity::class.java)
        }
        startActivity(intent)
    }
}
