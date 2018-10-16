package com.example.aria.day14_ballanimator

import android.animation.ValueAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.value_animator_layout.*

class ValueAnimatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.value_animator_layout)
        overridePendingTransition(0, 0)
        valueAnimator()
        value_go.setOnClickListener { animator.start() }
    }
    private lateinit var animator: ValueAnimator
    fun valueAnimator() {
        animator= ValueAnimator.ofFloat(0.0f, -500.0f, 0.0f)
        animator.duration = 600
        animator.addUpdateListener {
            Log.wtf("aaaa","valueAnimator()")
            value_img.translationY = it.animatedValue as Float
        }
    }

}