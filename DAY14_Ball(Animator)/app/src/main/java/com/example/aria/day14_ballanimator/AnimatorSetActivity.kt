package com.example.aria.day14_ballanimator

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.animator_set_layout.*

class AnimatorSetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.animator_set_layout)
        animatorSet()

//        val animup = ObjectAnimator.ofFloat(set_img_down, "translationY", 0.0f, -1000f, 0.0f)
//        val animdown = ObjectAnimator.ofFloat(set__img_up, "translationY", 0.0f, 1000f, 0.0f)
//        sameAnim = AnimatorSet()
//        sameAnim.duration = 600
        same.setOnClickListener {
//            sameAnim.playTogether(animup, animdown)
            sameAnim.start()
        }
        one_by_one.setOnClickListener {
//            sameAnim.play(animup).after(animdown)
            oneByOneAnim.start()
        }
    }

    private lateinit var oneByOneAnim: AnimatorSet
    private lateinit var sameAnim: AnimatorSet
    fun animatorSet() {
        val animup = ObjectAnimator.ofFloat(set_img_down, "translationY", 0.0f, -1000f, 0.0f)
        val animdown = ObjectAnimator.ofFloat(set__img_up, "translationY", 0.0f, 1000f, 0.0f)
        oneByOneAnim = AnimatorSet()
        oneByOneAnim.setDuration(600)
                .play(animup).after(animdown)
        sameAnim = AnimatorSet()
        sameAnim.setDuration(600)
                .playTogether(animup, animdown)
    }
}