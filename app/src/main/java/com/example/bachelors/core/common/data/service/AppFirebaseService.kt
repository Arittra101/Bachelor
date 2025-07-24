package com.example.bachelors.core.common.data.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.bachelors.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class AppFirebaseService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "fcm token: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d("FCM", "From: ${remoteMessage.from}")

        // Check if message contains a notification payload
        remoteMessage.notification?.let {
            Log.d("FCM", "Notification Body: ${it.body}")
            showNotification(it.title ?: "Notification", it.body ?: "")
        }

        // Check if message contains a data payload
        if (remoteMessage.data.isNotEmpty()) {
            Log.d("FCM", "Message data: ${remoteMessage.data}")
            // Handle your data here
        }
    }

    private fun showNotification(title: String, body: String) {
        Log.d("FCM", "showNotification: $title $body")
        val channelId = "default_channel"

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            channelId,
            "Default Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
        val notificationId = System.currentTimeMillis().toInt()

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        notificationManager.notify(notificationId, notificationBuilder.build())
    }

}