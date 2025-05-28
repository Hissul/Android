package com.example.topacademy_android.second.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.topacademy_android.second.presentation.event.SecondUiEvent

class SecondViewModel : ViewModel() {

    private val _uiEvent = MutableLiveData<SecondUiEvent>()
    val uiEvent: LiveData<SecondUiEvent> = _uiEvent

    fun onWeatherClicked() {
        _uiEvent.value = SecondUiEvent.NavigateToWeather
    }

    fun onCalculatorClicked() {
        _uiEvent.value = SecondUiEvent.NavigateToCalculator
    }

    fun onListClicked() {
        _uiEvent.value = SecondUiEvent.NavigateToCarList
    }
}