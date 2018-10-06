package com.example.fish.day8_bottomnavigationfragmentintent

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_other.*

class Activity_3: AppCompatActivity() {
    val intent = intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        init()
        other_button.setOnClickListener { intent.intent(this, MainActivity())}
    }

    fun init(){
        other_textView.setText("H2ME")
        other_button.setText("BYE")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}