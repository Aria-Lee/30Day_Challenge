package com.example.fish.day20_activitytransition

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.transition.Slide
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.Window
import kotlinx.android.synthetic.main.activity_main.*

class Activity_2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        setEnterAnimation()
    }

    fun setEnterAnimation(){
        when(intent.getStringExtra("Transition")){
            "android.transition.Explode" ->{
                val explode = Explode()
                explode.duration = 1000
                window.enterTransition = explode
            }

            "android.transition.Slide" ->{
                val slide = Slide()
                slide.duration = 1000
                window.enterTransition = slide
            }

            "android.transition.Fade" ->{
                val fade = TransitionInflater.from(this).inflateTransition(R.transition.activity_fade)
                window.enterTransition = fade
            }
        }
    }

}
