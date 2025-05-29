package com.example.topacademy_android.main.domain.use_case

import android.util.Patterns
import com.example.topacademy_android.main.domain.model.ValidationResult

class ValidateLoginUseCase {

    fun execute(email : String, password: String) : ValidationResult{
        val emailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val passwordValid = password.length > 6

        return ValidationResult(emailValid, passwordValid)
    }

}