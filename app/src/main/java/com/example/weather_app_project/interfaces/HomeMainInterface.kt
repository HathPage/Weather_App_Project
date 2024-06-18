package com.example.weather_app_project.interfaces

interface HomeMainInterface {
    fun onLocationButtonClick()
    fun onCitySelected(city: String)
    fun onMenuButtonClick()
    fun onFragmentReady()
    fun onLoadReady()
}