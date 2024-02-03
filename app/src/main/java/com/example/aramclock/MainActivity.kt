package com.example.aramclock

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.aramclock.databinding.ActivityMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    val alarmManagerExample = AlarmManagerExample()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.selectTimeButton.setOnClickListener {
            showTimePicker()
        }
    }
    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(currentHour)
            .setMinute(currentMinute)
            .setTitleText("Select Time")
            .build()

        timePicker.addOnPositiveButtonClickListener {
            val selectedHour = timePicker.hour
            val selectedMinute = timePicker.minute
            sendAlarmNotification(selectedHour,selectedMinute)
            binding.alamTimeText.text = "$selectedHour : $selectedMinute"
            Toast.makeText(applicationContext,"Alarm oluşturldu!",Toast.LENGTH_SHORT).show()
        }

        timePicker.show(supportFragmentManager, "timePicker")
    }
    private fun sendAlarmNotification(hour : Int, minute:  Int) {
        alarmManagerExample.setAlarm(this, time = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }.timeInMillis)
    }
}