package com.example.topacademy_android.сalculator.data

import com.example.topacademy_android.сalculator.domain.repository.CalculatorRepository
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorRepositoryImpl : CalculatorRepository {

    override fun calculate(expression: String): CalculatorResult {
        return try {
            val result = ExpressionBuilder(expression).build().evaluate()
            val formatted = if (result % 1.0 == 0.0) result.toInt().toString() else result.toString()
            CalculatorResult.Success(formatted)
        } catch (e: Exception) {
            CalculatorResult.Error("Ошибка вычисления: ${e.localizedMessage}")
        }
    }
}