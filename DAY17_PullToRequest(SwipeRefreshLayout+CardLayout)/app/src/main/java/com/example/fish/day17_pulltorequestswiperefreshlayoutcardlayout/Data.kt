package com.example.fish.day17_pulltorequestswiperefreshlayoutcardlayout

import android.content.Context
import android.util.Log

class Data (val cardImage : Int, val cardName: String)

var list = mutableListOf<Data>()
class SetData(){
    fun setList(context: Context) : MutableList<Data>{
        for (i in 1..16){
            val name = "avatar_$i"
            val id = context.resources.getIdentifier(name, "drawable", context.packageName)
            list.add(Data(id, name))
        }
        return list
    }
}