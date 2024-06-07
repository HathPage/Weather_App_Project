package com.example.weather_app_project.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.weather_app_project.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ChangeDate {
    companion object {
        fun setDate(view: View) {
            val calendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat("MMM, dd", Locale.getDefault())
            val date = dateFormat.format(calendar.time)
            val dateTextView: TextView = view.findViewById(R.id.tv_date)
            dateTextView.text = date
        }
    }
}