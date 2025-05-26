package com.example.topacademy_android.network


data class DataSeries(
    val date: Int,
    val weather: String,
    val wind10m_max: Int,
    val temp2m: Temperature
)