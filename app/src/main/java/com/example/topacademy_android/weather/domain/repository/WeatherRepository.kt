package com.example.topacademy_android.weather.domain.repository

import com.example.topacademy_android.weather.domain.model.WeatherResponse

interface WeatherRepository {
    suspend fun getWeather(latitude: Double, longitude: Double): WeatherResponse
}