package com.example.topacademy_android.main.domain.validator

interface PasswordValidator {
    fun isValid(password: String): Boolean
}