package com.kz.dagdy.ui.calendar.utils

import android.app.TimePickerDialog
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object TimeUtils {
    fun showTimePickerDialog(
        fragment: Fragment,
        onTimeSetListener: TimePickerDialog.OnTimeSetListener
    ) {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            fragment.requireContext(),
            onTimeSetListener,
            hour,
            minute,
            true
        )
        timePickerDialog.show()
    }

    fun formatTime(hour: Int, minute: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)

        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return timeFormat.format(calendar.time)
    }
}