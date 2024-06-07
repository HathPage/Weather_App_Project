package com.example.weather_app_project.network.api

sealed class ApiResponse<T>(val data:T?=null, val message:String?=null) {
    class Success<T>(data:T):ApiResponse<T>(data)
    class Fail<T>(data: T?=null, message: String):ApiResponse<T>(data,message)
    class Loading<T>:ApiResponse<T>()
}