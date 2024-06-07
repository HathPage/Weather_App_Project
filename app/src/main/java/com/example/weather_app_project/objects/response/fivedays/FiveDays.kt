package com.example.weather_app_project.objects.response.fivedays


import com.google.gson.annotations.SerializedName

data class FiveDays(
    @SerializedName("city")
    var city: City,
    @SerializedName("cnt")
    var cnt: Int,
    @SerializedName("cod")
    var cod: String,
    @SerializedName("list")
    var list: List<ThreeHours>,
    @SerializedName("message")
    var message: Int
)