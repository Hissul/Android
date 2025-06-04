package com.example.topacademy_android.main.domain.validator

interface EmailValidator {
    fun isValid(email: String): Boolean
}