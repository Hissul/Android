package com.example.topacademy_android.сalculator.domain.repository

class CalculatorRepository {

    fun calculate(expression: String): String {
        return try {

            val result = net.objecthunter.exp4j.ExpressionBuilder(expression)
                .build()
                .evaluate()
            if (result % 1.0 == 0.0) result.toInt().toString()
            else result.toString()
        }
        catch (e: Exception) {
            "Ошибка"
        }
    }

}