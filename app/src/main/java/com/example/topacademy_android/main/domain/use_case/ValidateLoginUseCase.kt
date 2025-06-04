package com.example.topacademy_android.main.domain.use_case

import com.example.topacademy_android.main.domain.model.ValidationResult
import com.example.topacademy_android.main.domain.validator.LoginValidator

class ValidateLoginUseCase(
    private val validator: LoginValidator
) {
    fun execute(email: String, password: String): ValidationResult {
        return validator.validate(email, password)
    }
}