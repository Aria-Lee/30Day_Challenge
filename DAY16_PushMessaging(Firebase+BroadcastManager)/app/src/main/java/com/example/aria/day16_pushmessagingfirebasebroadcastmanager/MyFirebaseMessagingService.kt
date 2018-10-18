package com.example.aria.day16_pushmessagingfirebasebroadcastmanager

import android.app.AlertDialog
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    var broadcast= LocalBroadcastManager.getInstance(this)

    override fun onMessageReceived(message: RemoteMessage?) {
        sendBroadcast(message?.notification?.body)
    }

    fun sendBroadcast(message: String?) {
        val intent = Intent("MyMessage")
        intent.putExtra("message", message)
        broadcast.sendBroadcast(intent)
    }
}