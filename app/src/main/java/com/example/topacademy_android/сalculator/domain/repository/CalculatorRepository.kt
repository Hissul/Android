package com.example.topacademy_android.сalculator.domain.repository

import com.example.topacademy_android.сalculator.data.CalculatorResult

interface CalculatorRepository {
    fun calculate(expression: String): CalculatorResult
}