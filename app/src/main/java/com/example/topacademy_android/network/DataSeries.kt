package com.example.topacademy_android.network

import java.util.Date

data class DataSeries(
    val date: Int,
    val weather: String,
    val wind10m_max: Int,
    val temp2m: Temperature
)