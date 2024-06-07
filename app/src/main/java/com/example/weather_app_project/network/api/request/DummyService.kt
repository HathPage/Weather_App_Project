package com.example.weather_app_project.network.api.request

import com.example.weather_app_project.constants.ConstantApi
import com.example.weather_app_project.objects.response.TodayResponse
import com.example.weather_app_project.objects.response.fivedays.FiveDays
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DummyService {
    @GET("weather?q={location}&appid=6a8559e8dbeced0b31faf839c14b3c9d")
    suspend fun getToday(@Path("location") location: String = "Tinh Hung Yen"): Response<TodayResponse>
    @GET("forecast?q={location}&appid=6a8559e8dbeced0b31faf839c14b3c9d")
    suspend fun getFiveDay(@Path("location") location: String = "Tinh Hung Yen"): Response<FiveDays>
}