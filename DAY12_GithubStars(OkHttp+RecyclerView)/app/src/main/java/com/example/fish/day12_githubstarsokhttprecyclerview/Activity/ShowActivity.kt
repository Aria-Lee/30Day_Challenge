package com.example.fish.day12_githubstarsokhttprecyclerview.Activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.fish.day12_githubstarsokhttprecyclerview.Adapter
import com.example.fish.day12_githubstarsokhttprecyclerview.Data.ReposData
import com.example.fish.day12_githubstarsokhttprecyclerview.Data.UserData
import com.example.fish.day12_githubstarsokhttprecyclerview.R
import com.example.fish.day12_githubstarsokhttprecyclerview.R.id.recyclewView
import kotlinx.android.synthetic.main.show_layout.*
import java.net.URL

class ShowActivity : AppCompatActivity() {
    lateinit var userData: UserData
    lateinit var reposList: ArrayList<ReposData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_layout)
        getData()
        setUserView()
        initRecyclerView()
    }

    fun getData() {
        userData = intent.extras.get("UserData") as UserData
        reposList = intent.getParcelableArrayListExtra("ReposDataList")
    }

    fun setUserView() {
        avatar.setImageBitmap(userData.avatar)
        account.setText(userData.account)
        name.setText(userData.userName)
        reposNum.setText(userData.reposCount)
        followersNum.setText(userData.followers)
        followingNum.setText(userData.following)
    }

    fun initRecyclerView(){
        recyclewView.adapter = Adapter(this, reposList)
        recyclewView.layoutManager = LinearLayoutManager(this)
    }

}