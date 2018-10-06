package com.example.fish.day8_bottomnavigationfragmentintent

import android.app.Activity
import android.content.Context
import android.content.Intent
class intent() {
    fun intent(from: Context?, to: Context) {
        val intent = Intent(from, to::class.java)
        from!!.startActivity(intent)
    }
}