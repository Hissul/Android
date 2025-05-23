package com.example.topacademy_android.network

data class WeatherResponse(
    val product: String,
    val init: String,
    val dataseries: List<DataSeries>
)