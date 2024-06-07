package com.example.weather_app_project.utils
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Convert {
    companion object{
        @JvmStatic
        fun convertEpochTimeToString(dt: Int):String{
            val date = Date(dt * 1000L)
            val format = SimpleDateFormat("HH:00", Locale.getDefault())
            return format.format(date)
        }
    }
}