package com.example.fish.day21_layoutswitchrecyclerviewgridlayoutmanager

import android.content.Context

class Data (val cardImage : Int, val cardName: String, val year : Int, val sex : String)

var list = mutableListOf<Data>()
class SetData(){
    fun setList(context: Context) : MutableList<Data>{
        for (i in 1..16){
            val name = "avatar_$i"
            val id = context.resources.getIdentifier(name, "drawable", context.packageName)
            val year = i+18
            val sex = if(listOf(1,2,4,5,11,14,16).contains(i)) "Male" else "Female"
            list.add(Data(id, name, year, sex))
        }
        return list
    }
}