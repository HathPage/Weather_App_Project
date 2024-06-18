package com.example.weather_app_project.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather_app_project.network.api.ApiResponse
import com.example.weather_app_project.objects.response.fivedays.FiveDays
import com.example.weather_app_project.repositories.FiveDaysRepository
import kotlinx.coroutines.launch

class FiveDaysViewModel(application: Application, private val fivesDaysRepository: FiveDaysRepository):
    AndroidViewModel(application) {
    val fiveDaysData = MutableLiveData<ApiResponse<FiveDays>>()
    fun getFiveDays(location: String){
        fiveDaysData.value = ApiResponse.Loading()
        viewModelScope.launch {
            val response = fivesDaysRepository.getFiveDays(location)
            fiveDaysData.value = response
        }
    }
    fun getUserFiveDays(lat: String, long: String){
        fiveDaysData.value = ApiResponse.Loading()
        viewModelScope.launch {
            val response = fivesDaysRepository.getUserFiveDays(lat, long)
            fiveDaysData.value = response
        }
    }
}