package com.example.topacademy_android.main.presentation.event

sealed class LoginUiEvent {
    data class ShowToast(val message: String) : LoginUiEvent()
    data class NavigateToSecond(val username: String) : LoginUiEvent()
}