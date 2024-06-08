package com.example.weather_app_project.repositories

import com.example.weather_app_project.network.api.ApiResponse
import com.example.weather_app_project.network.api.GenericApiResponse
import com.example.weather_app_project.network.api.RetrofitClient
import com.example.weather_app_project.objects.response.TodayResponse

class TodayRepository: GenericApiResponse() {
    suspend fun getToday(location: String): ApiResponse<TodayResponse> {
        return apiCall { RetrofitClient.getDummyApi.getToday(location) }
    }
}