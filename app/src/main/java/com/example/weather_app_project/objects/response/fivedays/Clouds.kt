package com.example.weather_app_project.objects.response.fivedays


import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    var all: Int
)