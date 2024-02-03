package com.example.aramclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

class AlarmManagerExample {
    fun setAlarm(context: Context,time : Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // AlarmManager ile alarmın tetiklenme zamanını ve tipini ayarlıyoruz.
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 60000,pendingIntent)
    }
}