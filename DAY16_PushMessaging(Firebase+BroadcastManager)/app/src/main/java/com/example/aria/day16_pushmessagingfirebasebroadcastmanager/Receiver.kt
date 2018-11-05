package com.example.aria.day16_pushmessagingfirebasebroadcastmanager

import android.app.AlertDialog
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.ContextThemeWrapper
import android.widget.Toast

class Receiver(val myContext: Context):BroadcastReceiver() {
    lateinit var manager: NotificationManager
    lateinit var builder: Notification.Builder

    override fun onReceive(context: Context?, intent: Intent?) {
        manager.notify(0, builder.build())
        showDialog(intent)

    }

    fun noti() {
        manager = myContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("Day16", "Day16", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
            builder = Notification.Builder(myContext, "Day16")
        } else {
            builder = Notification.Builder(myContext)
        }

        builder.setSmallIcon(R.drawable.abc_ic_star_black_36dp)
                .setContentTitle("Day16")
                .setContentText("Day16 Challenge")
                .setAutoCancel(true)

    }

    fun showDialog( intent: Intent?){
        AlertDialog.Builder(myContext)
                .setTitle("Alert")
                .setMessage(intent!!.getStringExtra("message"))
                .setPositiveButton("OK"){
                    dialog, which -> dialog.cancel()
                }.show()
    }
}