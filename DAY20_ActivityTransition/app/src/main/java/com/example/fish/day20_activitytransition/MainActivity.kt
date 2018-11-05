package com.example.fish.day20_activitytransition

import android.app.ActivityOptions
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.transition.*
import android.view.View
import android.view.Window.FEATURE_CONTENT_TRANSITIONS
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var explode: Transition
    lateinit var slide: Transition
    lateinit var fade: Transition

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTransition()
        textView.setOnClickListener(listener)
        explode_button.setOnClickListener(listener)
        slide_button.setOnClickListener(listener)
        fade__button.setOnClickListener(listener)
    }

    fun initTransition(){
        explode = Explode()
        explode.duration = 1000

        slide = Slide()
        slide.duration = 1000

        fade = TransitionInflater.from(this).inflateTransition(R.transition.activity_fade)
    }

    val listener = View.OnClickListener {
        when (it){
            textView ->{
                val intent = Intent(this, Activity_2::class.java)
                val sunPair =android.util.Pair(sun as View, "sun")
                val textPair =android.util.Pair(textView as View, "text")

                val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this,sunPair, textPair)
                startActivity(intent, transitionActivityOptions.toBundle())
            }

            explode_button ->{
                startIntent(explode)
            }

            slide_button ->{
                startIntent(slide)
            }

            fade__button ->{
                startIntent(fade)
            }
        }
    }

    fun startIntent(transition: Transition){
        val intent = Intent(this, Activity_2::class.java)
        window.exitTransition = transition
        intent.putExtra("Transition", transition.name)
        startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }
}
