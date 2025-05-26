package com.example.topacademy_android.viewModels

import androidx.lifecycle.ViewModel
import com.example.topacademy_android.useCases.CalculatorUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CalculatorViewModel(private val useCase: CalculatorUseCase) : ViewModel(){

    private val _expression = MutableStateFlow("")
    val expression: StateFlow<String> = _expression

    fun appendSymbol(symbol: String) {
        _expression.value += symbol
    }

    fun clear() {
        _expression.value = ""
    }

    fun evaluate() {
        _expression.value = useCase.evaluateExpression(_expression.value)
    }

    fun backspace() {
        _expression.value = _expression.value.dropLast(1)
    }

}