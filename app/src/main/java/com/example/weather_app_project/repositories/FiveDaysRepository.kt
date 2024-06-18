package com.example.weather_app_project.repositories

import com.example.weather_app_project.network.api.ApiResponse
import com.example.weather_app_project.network.api.GenericApiResponse
import com.example.weather_app_project.network.api.RetrofitClient
import com.example.weather_app_project.objects.response.fivedays.FiveDays

class FiveDaysRepository: GenericApiResponse() {
    suspend fun getFiveDays(location: String): ApiResponse<FiveDays> {
        return apiCall { RetrofitClient.getDummyApi.getFiveDay(location) }
    }
    suspend fun getUserFiveDays(lat: String, long: String): ApiResponse<FiveDays> {
        return apiCall { RetrofitClient.getDummyApi.getUserFiveDay(lat, long) }
    }
}