package com.example.fish.day13_localstoragesharedpreferences

import android.content.Context
import android.util.Log

class sharedPreference(context: Context) {
    private val pref = context.getSharedPreferences("Name", Context.MODE_PRIVATE)

    fun saveName(name: String) {
        Log.wtf("aaaa", name)
        pref.edit().putString("name", name).apply()
    }

    fun getName() : String{
        return pref.getString("name", "")
    }
}