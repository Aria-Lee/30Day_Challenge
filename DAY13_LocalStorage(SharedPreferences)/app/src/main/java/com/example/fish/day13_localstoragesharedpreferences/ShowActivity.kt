package com.example.fish.day13_localstoragesharedpreferences

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.show_layout.*

class ShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_layout)

        val pref = sharedPreference(this)
        show.setOnClickListener { textView.setText("${pref.getName()}") }
    }
}