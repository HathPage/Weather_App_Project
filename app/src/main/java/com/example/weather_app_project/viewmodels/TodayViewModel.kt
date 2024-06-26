package com.example.weather_app_project.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather_app_project.network.api.ApiResponse
import com.example.weather_app_project.objects.response.TodayResponse
import com.example.weather_app_project.repositories.TodayRepository
import kotlinx.coroutines.launch

class TodayViewModel(application: Application, private val todayRepository: TodayRepository):
    AndroidViewModel(application) {
    val todayData = MutableLiveData<ApiResponse<TodayResponse>>()
    fun getToday(location: String){
        todayData.value = ApiResponse.Loading()
        viewModelScope.launch {
            val response = todayRepository.getToday(location)
            todayData.value = response
        }
    }
    fun getUserToday(lat: String, long: String){
        todayData.value = ApiResponse.Loading()
        viewModelScope.launch {
            val response = todayRepository.getUserToday(lat, long)
            todayData.value = response
        }
    }
}