package com.example.topacademy_android.calculator.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.topacademy_android.calculator.data.CalculatorResult
import com.example.topacademy_android.calculator.domain.use_case.CalculatorUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CalculatorViewModel(private val useCase: CalculatorUseCase) : ViewModel(){

    private val _expression = MutableStateFlow("")
    val expression: StateFlow<String> = _expression

    fun clear() {
        _expression.value = ""
    }

    fun evaluate(expression: String) {
        val result = useCase.evaluateExpression(expression)
        when (result) {
            is CalculatorResult.Success -> _expression.value = result.result
            is CalculatorResult.Error -> _expression.value = result.message
        }
    }

    fun backspace() {
        _expression.value = _expression.value.dropLast(1)
    }

}