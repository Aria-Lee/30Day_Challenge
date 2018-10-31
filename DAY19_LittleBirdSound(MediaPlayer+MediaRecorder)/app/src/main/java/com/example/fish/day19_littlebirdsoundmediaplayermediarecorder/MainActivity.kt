package com.example.fish.day19_littlebirdsoundmediaplayermediarecorder

import android.animation.ObjectAnimator
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer
    lateinit var animator : ObjectAnimator
    lateinit var thread : Thread
    val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityInit()


    }

    fun  activityInit(){
        mediaPlayer = MediaPlayer.create(this, R.raw.country_cue_1)
        animator = ObjectAnimator.ofFloat(imageView, "rotation", 0.0f, 360.0f)
        animator.duration = 2000
        animator.interpolator = LinearInterpolator()
        startAndPause.setOnClickListener(playListener)
        stop.setOnClickListener(playListener)
        seekBar.setOnSeekBarChangeListener(seekBarListener)
        this.progressSeekBar.max = mediaPlayer.duration
        progressSeekBar.setOnSeekBarChangeListener(progressSeekBarListener)
        mediaPlayer.setOnCompletionListener(mediaPlayerListener)
        setThread()
    }

    fun setThread(){
        thread = Thread(Runnable {
            if ( progressSeekBar.progress < progressSeekBar.max ) {
                progressSeekBar.progress = mediaPlayer.currentPosition
            }
            handler.postDelayed(thread, 200)
        })
    }








    val playListener = View.OnClickListener {
        when (it) {
            startAndPause -> {
                if (mediaPlayer.isPlaying) {
                    startAndPause.setText("START")
                    animator.pause()
                    mediaPlayer.pause()
                    handler.removeCallbacks(thread)
                } else {
                    if(animator.isPaused) animator.resume()
                    else{
                    animator.repeatCount = -1
                    animator.start()
                        println("aaaaaa $animator")

                    }
                    startAndPause.setText("PAUSE")
                    startAndPause.setText("PAUSE")
                    mediaPlayer.start()
                    handler.post(thread)
                }
            }

            stop -> {
                animator.end()
                mediaPlayer.stop()
                mediaPlayer.prepare()
//                mediaPlayer.reset()
                startAndPause.setText("START")
            }
        }
    }

    val seekBarListener = object : SeekBar.OnSeekBarChangeListener{
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            mediaPlayer.setVolume(progress/100f,progress/100f)
            textView.text = "Volume : $progress %"
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    }

    val progressSeekBarListener = object :SeekBar.OnSeekBarChangeListener{
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if(fromUser)  mediaPlayer.seekTo(progress)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
            handler.removeCallbacks(thread)
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            handler.post(thread)
        }

    }

    val mediaPlayerListener = MediaPlayer.OnCompletionListener{
        mediaPlayer.stop()
        mediaPlayer.prepare()
        animator.end()
        progressSeekBar.progress = 0
        startAndPause.text = "Start"
    }
}
