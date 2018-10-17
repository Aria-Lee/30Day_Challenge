package com.example.aria.day15_notificationnotificationmanager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        noti()
        button.setOnClickListener { manager.notify(0, builder.build())
        }
    }

    lateinit var manager: NotificationManager
    lateinit var builder : Notification.Builder

    fun noti() {
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("Day15", "Day15", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
            builder = Notification.Builder(this, "Day15")
        } else {
            builder = Notification.Builder(this)
        }

        builder.setSmallIcon(R.drawable.ic_directions_walk_black_24dp)
                .setContentTitle("Day15")
                .setContentText("Day15 Challenge")
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.ic_wc_black_24dp))
//                .setPriority(Notification.PRIORITY_HIGH)
//                .setDefaults(Notification.DEFAULT_ALL)
//                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
    }
}
