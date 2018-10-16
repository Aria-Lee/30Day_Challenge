package com.example.aria.day14_ballanimator

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.*
import kotlinx.android.synthetic.main.interpolator_layout.*
import kotlinx.android.synthetic.main.value_animator_layout.*

class InterpolatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.interpolator_layout)

        anim = ObjectAnimator.ofFloat(inter_img, "translationX", 0f, 1000f, 0f)

        accelerate.setOnClickListener (listener)
        accelerateDecelerate.setOnClickListener(listener)
        anticipate.setOnClickListener(listener)
        anticipateOvershoot.setOnClickListener(listener)
        bounce.setOnClickListener(listener)
        cycle.setOnClickListener(listener)
        decelerate.setOnClickListener(listener)
        linear.setOnClickListener(listener)
        overshoot.setOnClickListener(listener)
        path.setOnClickListener(listener)
    }

    private lateinit var anim: ObjectAnimator

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    val listener = View.OnClickListener {
        anim.interpolator =
                when (it) {
                    accelerate -> AccelerateInterpolator()
                    accelerateDecelerate -> AccelerateDecelerateInterpolator()
                    anticipate -> AnticipateInterpolator()
                    anticipateOvershoot -> AnticipateOvershootInterpolator()
                    bounce -> BounceInterpolator()
                    cycle -> CycleInterpolator(1f)
                    decelerate -> DecelerateInterpolator()
                    linear -> LinearInterpolator()
                    overshoot -> OvershootInterpolator()
                    path -> PathInterpolator(0f, 0f)
                    else -> null
                }
        anim.start()
    }
}