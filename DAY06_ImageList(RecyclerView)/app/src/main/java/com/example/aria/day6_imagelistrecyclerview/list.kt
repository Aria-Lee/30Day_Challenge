package com.example.aria.day6_imagelistrecyclerview

import android.content.Context

//var list =  mutableListOf(
//        content(R.drawable.luffy, "モンキー・D・ルフィ" ),
//        content(R.drawable.roronoa_zoro, "ロロノア・ゾロ" ),
//        content(R.drawable.nami, "ナミ" ),
//        content(R.drawable.usopp, "ウソップ" ),
//        content(R.drawable.vinsmoke_sanji, "ヴィンスモーク・サンジ" ),
//        content(R.drawable.tony_tony_chopper, "トニートニー・チョッパー" ),
//        content(R.drawable.nico_robin, "ニコ・ロビン" ),
//        content(R.drawable.franky, "フランキー" ),
//        content(R.drawable.brook, "ブルック" )
//        )

class content ( val image : Int, val text: String)

var list = mutableListOf<content>()
class SetData(){
        fun setList(context: Context) : MutableList<content>{
                for (i in 1..10){
                        val name = "background_$i"
                        val id = context.resources.getIdentifier(name, "drawable", context.packageName)
                        list.add(content(id, name))
                }
                return list
        }
}