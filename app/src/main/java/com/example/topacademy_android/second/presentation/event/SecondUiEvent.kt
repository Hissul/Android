package com.example.topacademy_android.second.presentation.event

sealed class SecondUiEvent {
    data object NavigateToWeather : SecondUiEvent()
    data object NavigateToCalculator : SecondUiEvent()
    data object NavigateToCarList : SecondUiEvent()
}