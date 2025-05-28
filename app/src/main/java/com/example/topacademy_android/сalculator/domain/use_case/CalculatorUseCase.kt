package com.example.topacademy_android.сalculator.domain.use_case

import com.example.topacademy_android.сalculator.domain.repository.CalculatorRepository


class CalculatorUseCase(private val repository: CalculatorRepository) {

    fun evaluateExpression(expression: String): String {
        return repository.calculate(expression)
    }

}