package com.example.weather_app_project.interfaces

import com.example.weather_app_project.objects.response.TodayResponse

interface OnItemClickListener {
    fun onItemClick(todayResponse: TodayResponse)
}