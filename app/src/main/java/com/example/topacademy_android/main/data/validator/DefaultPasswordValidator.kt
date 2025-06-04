package com.example.topacademy_android.main.data.validator

import com.example.topacademy_android.main.domain.validator.PasswordValidator

class DefaultPasswordValidator : PasswordValidator {
    override fun isValid(password: String): Boolean {
        return password.length > 6
    }
}