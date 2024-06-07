package com.example.weather_app_project.objects.response


import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("1h")
    var h: Double
)