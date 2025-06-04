package com.example.topacademy_android.weather.domain.usecase

import com.example.topacademy_android.weather.domain.repository.WeatherRepository

class GetWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(lat: Double, lon: Double) = repository.getWeather(lat, lon)
}