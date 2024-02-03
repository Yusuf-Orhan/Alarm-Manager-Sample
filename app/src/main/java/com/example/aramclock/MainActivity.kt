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

    // ViewBinding kullanarak layout elemanlarına erişim sağlamak için gerekli olan binding değişkeni
    private lateinit var binding : ActivityMainBinding

    // AlarmManagerExample sınıfından bir örnek oluşturuluyor
    private val alarmManagerExample = AlarmManagerExample()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding kullanılarak layout dosyası ile etkileşim için gerekli olan binding oluşturuluyor
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // selectTimeButton elemanına tıklandığında showTimePicker fonksiyonu çağrılır
        binding.selectTimeButton.setOnClickListener {
            showTimePicker()
        }
    }

    // TimePicker'ı gösteren fonksiyon
    private fun showTimePicker() {
        // Güncel zamanı al
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        // MaterialTimePicker ile kullanıcıya saat seçtirilir
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(currentHour)
            .setMinute(currentMinute)
            .setTitleText("Select Time")
            .build()

        // Kullanıcı saat seçtikten sonra çalışacak işlemler
        timePicker.addOnPositiveButtonClickListener {
            val selectedHour = timePicker.hour
            val selectedMinute = timePicker.minute

            // Seçilen saat ve dakikayı kullanarak alarm bildirimini gönder
            sendAlarmNotification(selectedHour, selectedMinute)

            // Seçilen saat ve dakikayı ekrana yazdır
            binding.alamTimeText.text = "$selectedHour : $selectedMinute"

            // Kullanıcıya alarm oluşturulduğuna dair bir bildirim göster
            Toast.makeText(applicationContext, "Alarm oluşturuldu!", Toast.LENGTH_SHORT).show()
        }

        // TimePicker'ı göster
        timePicker.show(supportFragmentManager, "timePicker")
    }

    // Alarm bildirimi gönderen fonksiyon
    private fun sendAlarmNotification(hour: Int, minute: Int) {
        // AlarmManagerExample sınıfındaki setAlarm fonksiyonu ile alarmı ayarla
        alarmManagerExample.setAlarm(this, time = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }.timeInMillis)
    }
}
