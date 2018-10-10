package com.example.aria.day10_progresscontrolprogressbarhandler

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.example.aria.day10_progresscontrolprogressbarhandler.R.id.progressBar
import kotlinx.android.synthetic.main.activity_main.*

class ProgressTask: AsyncTask<Context, Int, Int>() {
    lateinit var progressBar : ProgressBar
    lateinit var textView : TextView

    override fun doInBackground(vararg context: Context): Int {
        while ( progressBar.progress < progressBar.max ){
            Thread.sleep(500)
            progressBar.incrementProgressBy(10)
            publishProgress()

        }
        return progressBar.progress
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        textView.setText("${progressBar.progress} %")
    }

}