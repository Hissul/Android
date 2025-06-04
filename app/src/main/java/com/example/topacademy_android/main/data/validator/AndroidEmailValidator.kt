package com.example.topacademy_android.main.data.validator

import android.util.Patterns
import com.example.topacademy_android.main.domain.validator.EmailValidator

class AndroidEmailValidator : EmailValidator {
    override fun isValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}