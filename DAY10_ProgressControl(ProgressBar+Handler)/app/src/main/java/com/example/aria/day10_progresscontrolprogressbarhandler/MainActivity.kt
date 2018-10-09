package com.example.aria.day10_progresscontrolprogressbarhandler

import android.annotation.TargetApi
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Thread.sleep

@TargetApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        play.setOnClickListener(mOnClickListener)
        pause.setOnClickListener(mOnClickListener)
        stop.setOnClickListener(mOnClickListener)
    }

    val handler = Handler()
    @RequiresApi(Build.VERSION_CODES.N)
    val progressIncreat = Runnable {
        progressBar.progress
//        {
//            sleep(1000)
            progressBar.incrementProgressBy(10)
            Log.wtf("aaaa", "${progressBar.progress}")
//            uiChange()
        textView.setText("${progressBar.progress} %")

//        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun uiChange(){
        this@MainActivity.runOnUiThread {
            progressBar.setProgress(progressBar.progress, true)
            textView.setText("${progressBar.progress} %")
            Log.wtf("aaaa", "uiChange()"+ "${progressBar.progress} %")

        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    val mOnClickListener = View.OnClickListener { it ->
        when (it) {
            play -> {
                handler.postDelayed(progressIncreat, 1000)
            }

            pause -> {
                handler.removeCallbacks(progressIncreat)
            }

            stop -> {
                handler.removeCallbacks(progressIncreat)
                progressBar.setProgress(0,true)
                textView.setText("0 %")
            }

        }
    }


}
