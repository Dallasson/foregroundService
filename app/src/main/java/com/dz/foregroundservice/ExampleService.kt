package com.dz.foregroundservice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class ExampleService : Service() {

    private val notificationId = "notificationId"
    private val notificationString = "foreground"


    override fun onBind(intent: Intent?): IBinder? = null
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()

        Intent(this,MainActivity::class.java).apply {
            val pendingIntent = PendingIntent.getActivity(this@ExampleService,1212,this,PendingIntent.FLAG_UPDATE_CURRENT)
            val notificationCompat = NotificationCompat.Builder(this@ExampleService,notificationId)
                .setContentTitle("Foreground Service")
                .setContentText("Starting Foreground Service..")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .build()

            startForeground(1,notificationCompat)
        }
        return START_STICKY
    }

    private fun createNotificationChannel() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel  = NotificationChannel(
                notificationId,notificationString,NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(notificationChannel)
        }
    }

    override fun onDestroy() {
        stopForeground(true)
        stopSelf()
        super.onDestroy()
    }
}