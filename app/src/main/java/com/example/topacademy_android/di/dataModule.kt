package com.example.topacademy_android.di

import com.example.topacademy_android.calculator.domain.repository.CalculatorRepository
import com.example.topacademy_android.calculator.data.CalculatorRepositoryImpl
import com.example.topacademy_android.main.domain.validator.EmailValidator
import com.example.topacademy_android.main.domain.validator.PasswordValidator
import org.koin.dsl.module
import com.example.topacademy_android.main.data.validator.AndroidEmailValidator
import com.example.topacademy_android.main.data.validator.DefaultPasswordValidator

val dataModule = module {
    single<CalculatorRepository> { CalculatorRepositoryImpl() }

    // Валидаторы (реализации)
    single<EmailValidator> { AndroidEmailValidator() }
    single<PasswordValidator> { DefaultPasswordValidator() }
}