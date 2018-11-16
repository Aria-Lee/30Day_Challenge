package com.example.aria.day22_localdatabasesqlitesqliteopenhelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class MySQLDatabase(val context: Context?, val name: String?, val factory: SQLiteDatabase.CursorFactory?, val version: Int) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val name = ""

    }

    fun a(){
     }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}