package com.example.topacademy_android.useCases

import com.example.topacademy_android.repositories.CalculatorRepository


class CalculatorUseCase(private val repository: CalculatorRepository) {

    fun evaluateExpression(expression: String): String {
        return repository.calculate(expression)
    }

}