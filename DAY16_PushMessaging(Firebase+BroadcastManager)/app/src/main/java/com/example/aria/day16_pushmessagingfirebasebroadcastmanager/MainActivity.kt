package com.example.aria.day16_pushmessagingfirebasebroadcastmanager

import android.app.AlertDialog
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import com.google.firebase.messaging.RemoteMessage

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reciever = Reciever(this)
        reciever.noti()

    }

    lateinit var reciever : Reciever
    override fun onStart() {
        super.onStart()
        LocalBroadcastManager.getInstance(this).registerReceiver(reciever, IntentFilter("MyMessage"))
    }

    override fun onStop() {
        super.onStop()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(reciever)
    }
}
