package com.example.topacademy_android.main.domain.validator

import com.example.topacademy_android.main.domain.model.ValidationResult

class LoginValidator(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
) {
    fun validate(email: String, password: String): ValidationResult {
        val emailValid = emailValidator.isValid(email)
        val passwordValid = passwordValidator.isValid(password)
        return ValidationResult(emailValid, passwordValid)
    }
}