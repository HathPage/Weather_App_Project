package com.example.weather_app_project.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather_app_project.repositories.TodayRepository

class TodayViewModelFactory(val application: Application, val todayRepository: TodayRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TodayViewModel(application, todayRepository) as T
    }
}