package com.example.aria.day14_ballanimator

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.object_animator_layout.*

class ObjectAnimatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.object_animator_layout)
        objectAnimator()
        object_go.setOnClickListener{valueAnim.start()}
    }

    private lateinit var valueAnim : ObjectAnimator
    fun objectAnimator(){
        valueAnim = ObjectAnimator.ofFloat(object_img2, "rotationY", 0.0f, 360.0f)
        valueAnim.setDuration(600)
    }
}