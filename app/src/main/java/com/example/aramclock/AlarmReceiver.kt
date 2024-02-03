package com.example.aramclock

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

// AlarmReceiver sınıfı, bir alarm tetiklendiğinde çalışan bir BroadcastReceiver sınıfıdır.
class AlarmReceiver : BroadcastReceiver() {

    // onReceive fonksiyonu, BroadcastReceiver sınıfının ana işlevini gerçekleştirir.
    override fun onReceive(context: Context, intent: Intent) {

        // Alarm tetiklendiğinde bildirim oluşturan createNotification fonksiyonunu çağırır.
        createNotification(context, "Alarm Clock", "Kurduğunuz alarmın süresi doldu!")
    }

    // createNotification fonksiyonu, bildirim oluşturur ve gösterir.
    private fun createNotification(context: Context, title: String, content: String) {

        // NotificationManager'ı al
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Android 8.0 ve üzeri için bildirim kanalı oluştur
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "alarm_channel_id"
            val channel = NotificationChannel(
                channelId,
                "Alarm Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        // Bildirim oluştur
        val notificationBuilder = NotificationCompat.Builder(context, "alarm_channel_id")
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Bildirimi göster
        notificationManager.notify(1, notificationBuilder.build())
    }
}