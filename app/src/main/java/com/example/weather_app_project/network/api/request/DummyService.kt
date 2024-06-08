package com.example.weather_app_project.network.api.request

import com.example.weather_app_project.constants.ConstantApi
import com.example.weather_app_project.objects.response.TodayResponse
import com.example.weather_app_project.objects.response.fivedays.FiveDays
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DummyService {
    @GET("weather")
    suspend fun getToday(
        @Query("q") location: String,
        @Query("appid") appId: String = "6a8559e8dbeced0b31faf839c14b3c9d"
    ): Response<TodayResponse>

    @GET("forecast")
    suspend fun getFiveDay(
        @Query("q") location: String,
        @Query("appid") appId: String = "6a8559e8dbeced0b31faf839c14b3c9d"
    ): Response<FiveDays>
}