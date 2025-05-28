package com.example.topacademy_android.weather.domain.model

data class WeatherResponse(
    val product: String,
    val init: String,
    val dataseries: List<DataSeries>
)