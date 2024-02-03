package com.example.aramclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aramclock.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private val notificationAlarmScheduler by lazy {
        NotificationAlarmScheduler(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.selectTimeButton.setOnClickListener {
            val reminderItem = ReminderItem(
                time = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, 16)
                    set(Calendar.MINUTE, 54)
                }.timeInMillis,
                id = 1,
            )
            notificationAlarmScheduler.schedule(reminderItem)
        }
    }
}