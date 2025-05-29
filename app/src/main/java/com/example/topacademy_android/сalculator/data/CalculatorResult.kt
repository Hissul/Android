package com.example.topacademy_android.сalculator.data

sealed class CalculatorResult {
    data class Success(val result: String) : CalculatorResult()
    data class Error(val message: String) : CalculatorResult()
}