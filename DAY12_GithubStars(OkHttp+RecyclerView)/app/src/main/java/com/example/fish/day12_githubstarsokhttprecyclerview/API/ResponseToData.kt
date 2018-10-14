package com.example.fish.day12_githubstarsokhttprecyclerview.API

import android.graphics.BitmapFactory
import android.util.Log
import com.example.fish.day12_githubstarsokhttprecyclerview.Data.ReposData
import com.example.fish.day12_githubstarsokhttprecyclerview.Data.UserData
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class ResponseToData(val response: Response?) {

    fun getUserData(): UserData {
        val responseStr = response!!.body()!!.string()
        val itemList = JSONObject(responseStr)
        val name = if ("${itemList.get("name")}" == "null") ""  else  "${itemList.get("name")}"
        val bitmap = BitmapFactory.decodeStream(URL("${itemList.get("avatar_url")}").openConnection().getInputStream())
        val userData = UserData(
            "${itemList.get("login")}",
            name,
            bitmap,
            "${itemList.get("public_repos")}",
            "${itemList.get("followers")}",
            "${itemList.get("following")}"
        )
        return userData
    }

    fun getReposData(): ArrayList<ReposData> {
        val responseStr = response!!.body()!!.string()
        val itemList = JSONArray(responseStr)
        var reposDataList= arrayListOf<ReposData>()
        for (i in 0..itemList.length()-1) {
            val item = itemList[i] as JSONObject
            val creatTime = item.get("created_at").toString().replace("T"," ").replace("Z", "")
            val updateTime = item.get("updated_at").toString().replace("T"," ").replace("Z", "")
            val reposData = ReposData(
                "${item.get("name")}",
                "${item.get("watchers_count")}",
                "${item.get("forks_count")}",
                "${item.get("stargazers_count")}",
                "${item.get("language")}",
                creatTime,
                updateTime
            )
            reposDataList.add(reposData)
        }
        return reposDataList
    }
}