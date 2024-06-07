package com.example.weather_app_project.utils

import java.util.Calendar

class GetDay {
    companion object{
        @JvmStatic
        fun getDay(): List<String> {
            val listDay =
                listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
            val calendar = Calendar.getInstance()
            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
            return listDay.drop(dayOfWeek - 1) + listDay.take(dayOfWeek - 1)
        }
    }
}