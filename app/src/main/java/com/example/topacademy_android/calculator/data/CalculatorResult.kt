package com.example.topacademy_android.calculator.data

sealed class CalculatorResult {
    data class Success(val result: String) : CalculatorResult()
    data class Error(val message: String) : CalculatorResult()
}