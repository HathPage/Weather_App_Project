package com.example.weather_app_project.interfaces

import com.example.weather_app_project.objects.response.TodayResponse

interface SavedLocationInterface {
    fun onLocationButtonClick()
    fun onItemClick(todayResponse: TodayResponse)
}