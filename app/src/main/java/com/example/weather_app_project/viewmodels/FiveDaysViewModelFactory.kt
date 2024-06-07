package com.example.weather_app_project.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather_app_project.repositories.FiveDaysRepository
import com.example.weather_app_project.repositories.TodayRepository

class FiveDaysViewModelFactory(val application: Application, val fiveDaysRepository: FiveDaysRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FiveDaysViewModel(application, fiveDaysRepository) as T
    }
}