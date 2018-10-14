package com.example.fish.day12_githubstarsokhttprecyclerview.API

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.fish.day12_githubstarsokhttprecyclerview.Activity.MainActivity
import com.example.fish.day12_githubstarsokhttprecyclerview.Activity.ShowActivity
import com.example.fish.day12_githubstarsokhttprecyclerview.Data.ReposData
import com.example.fish.day12_githubstarsokhttprecyclerview.Data.UserData
import okhttp3.*
import java.io.IOException

class Okhttp(val context: Context) {
    private val client = OkHttpClient().newBuilder().build()
    private lateinit var responseToData: ResponseToData
    private lateinit var userData: UserData
    private lateinit var reposDataList: ArrayList<ReposData>
    private var alreadyGetUserData = 0
    private var alreadyGetReposData = 0

    fun getUserData(name: String) {
        val request = Request.Builder()
            .url("https://api.github.com/users/$name")
            .build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.wtf("aaaa", "fail")
            }

            override fun onResponse(call: Call?, response: Response?) {
                responseToData = ResponseToData(response)
                userData = responseToData.getUserData()
                alreadyGetUserData = 1
                intent()
            }
        })
    }

    fun getRepos(name: String) {
        val request = Request.Builder()
            .url("https://api.github.com/users/$name/repos")
            .build()
        // 建立Call
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.wtf("aaaa", "fail")
            }

            override fun onResponse(call: Call?, response: Response?) {
                responseToData = ResponseToData(response)
                reposDataList = responseToData.getReposData()
                alreadyGetReposData = 1
                intent()
            }
        })
    }

    fun intent() {
        if (alreadyGetUserData and alreadyGetReposData == 1) {
            alreadyGetUserData = 0
            alreadyGetReposData = 0
            (context as MainActivity).intent(userData, reposDataList)
        }
    }
}