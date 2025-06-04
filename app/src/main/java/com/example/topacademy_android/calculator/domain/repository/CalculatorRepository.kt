package com.example.topacademy_android.calculator.domain.repository

import com.example.topacademy_android.calculator.data.CalculatorResult

interface CalculatorRepository {
    fun calculate(expression: String): CalculatorResult
}