package com.example.topacademy_android.calculator.domain.use_case

import com.example.topacademy_android.calculator.data.CalculatorResult
import com.example.topacademy_android.calculator.domain.repository.CalculatorRepository


class CalculatorUseCase(private val repository: CalculatorRepository) {

    fun evaluateExpression(expression: String): CalculatorResult {
        return repository.calculate(expression)
    }

}