package com.example.topacademy_android.main.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.topacademy_android.main.domain.model.ValidationResult
import com.example.topacademy_android.main.domain.use_case.ValidateLoginUseCase
import com.example.topacademy_android.main.presentation.event.LoginUiEvent

class LoginViewModel(
    private val validateLoginUseCase: ValidateLoginUseCase
) : ViewModel() {

    private val _validationResult = MutableLiveData<ValidationResult>()
    val validationResult: LiveData<ValidationResult> = _validationResult

    private val _uiEvent = MutableLiveData<LoginUiEvent>()
    val uiEvent: LiveData<LoginUiEvent> = _uiEvent

    fun validate(email: String, password: String){
        val result = validateLoginUseCase.execute(email, password)
        _validationResult.value = result

        if (result.emailValid && result.passwordValid) {
            _uiEvent.value = LoginUiEvent.ShowToast("Добро пожаловать!")
            _uiEvent.value = LoginUiEvent.NavigateToSecond(email)
        }
    }
}

