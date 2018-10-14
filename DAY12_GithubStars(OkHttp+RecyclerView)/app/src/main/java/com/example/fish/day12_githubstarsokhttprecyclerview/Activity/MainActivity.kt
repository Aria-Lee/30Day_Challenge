package com.example.fish.day12_githubstarsokhttprecyclerview.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.fish.day12_githubstarsokhttprecyclerview.API.Okhttp
import com.example.fish.day12_githubstarsokhttprecyclerview.Data.ReposData
import com.example.fish.day12_githubstarsokhttprecyclerview.Data.UserData
import com.example.fish.day12_githubstarsokhttprecyclerview.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val okHttp = Okhttp(this)
        search.setOnClickListener {
            okHttp.getUserData("${editText.text}")
            okHttp.getRepos("${editText.text}")
        }
    }

    fun intent(userData: UserData, reposDataList: ArrayList<ReposData>){
        val intent = Intent(this, ShowActivity::class.java)
        intent.putExtra("UserData", userData)
        intent.putParcelableArrayListExtra("ReposDataList", reposDataList)
        startActivity(intent)
    }
}
