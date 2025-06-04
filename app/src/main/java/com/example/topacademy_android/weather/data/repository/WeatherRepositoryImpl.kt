package com.example.topacademy_android.weather.data.repository

import com.example.topacademy_android.weather.data.remote.api.WeatherApi
import com.example.topacademy_android.weather.domain.model.WeatherResponse
import com.example.topacademy_android.weather.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val api: WeatherApi) : WeatherRepository {
    override suspend fun getWeather(latitude: Double, longitude: Double): WeatherResponse {
        return api.getWeatherForecast(latitude = latitude, longitude = longitude)
    }
}