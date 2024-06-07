package com.example.weather_app_project.network.api

import retrofit2.Response
import java.lang.Exception

abstract class GenericApiResponse {
    suspend fun <T> apiCall(call:suspend ()-> Response<T>):ApiResponse<T>{
        try {
            val response = call()
            if(response.isSuccessful){
                val body = response.body()
                body?.let{data->    //data đang chính là body
                    return ApiResponse.Success(data)
                }

            }
            return ApiResponse.Fail(message = response.message())
        }catch (e: Exception){
            return ApiResponse.Fail(message = e.message!!)
        }
    }
}