package com.example.aria.day10_progresscontrolprogressbarhandler

import android.annotation.TargetApi
import android.os.AsyncTask
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.aria.day10_progresscontrolprogressbarhandler.R.id.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Thread.sleep

@TargetApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {
    lateinit var progressTask: ProgressTask
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initProgressTask()
        play.setOnClickListener(mOnClickListener)
        pause.setOnClickListener(mOnClickListener)
        stop.setOnClickListener(mOnClickListener)
    }

    val mOnClickListener = View.OnClickListener { it ->

        when (it) {
            play -> {
                if(progressTask.status == AsyncTask.Status.RUNNING){
                    Toast.makeText(this, "程序已在執行中", Toast.LENGTH_LONG).show()
                }
                else progressTask.execute(this)
            }

            pause -> {
                progressTask.cancel(true)
                initProgressTask()
            }

            stop -> {
                progressTask.cancel(true)
                progressBar.setProgress(0, true)
                textView.setText("0 %")
                initProgressTask()
            }

        }
    }

    fun initProgressTask(){
        progressTask = ProgressTask()
        progressTask.progressBar = progressBar
        progressTask.textView = textView
    }


}
