package com.example.android_notifications_tyler_clawson

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val NOTIFICATION_ID_INSTANT = 42
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_get_notification.setOnClickListener { p0 ->

            val contentIntent = Intent(this, FullscreenActivity::class.java)
            contentIntent.putExtra("stringID", "Notification Tapped")
            val pendingContentIntent = PendingIntent.getActivity(this, 0, contentIntent, PendingIntent.FLAG_ONE_SHOT)

            val contentIntent2 = Intent(this, FullscreenActivity::class.java)
            contentIntent2.putExtra("stringID", "Action Tapped")
            val pendingContentIntent2 = PendingIntent.getActivity(this, 1, contentIntent2, PendingIntent.FLAG_ONE_SHOT)

            val channelID = "$packageName.highchannel"
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "High Basic Notification Channel"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val description = "This is a basic notification"

                val channel = NotificationChannel(channelID, name, importance)
                channel.setDescription(description)

                notificationManager.createNotificationChannel(channel)

            }

            val notificationBuilder = NotificationCompat.Builder(this, channelID)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle("Basic Notification")
                .setContentText("Text for basic notification")
                .setColor(ContextCompat.getColor(this, R.color.colorAccent))
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setContentIntent(pendingContentIntent)
                .addAction(R.drawable.abc_ic_arrow_drop_right_black_24dp, "Action Title", pendingContentIntent2)
            notificationManager.notify(NOTIFICATION_ID_INSTANT, notificationBuilder.build())





        }
    }
}
