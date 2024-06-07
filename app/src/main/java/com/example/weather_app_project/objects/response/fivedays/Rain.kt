package com.example.weather_app_project.objects.response.fivedays


import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("3h")
    var h: Double
)