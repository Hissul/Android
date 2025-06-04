package com.example.topacademy_android.di

import com.example.topacademy_android.calculator.domain.use_case.CalculatorUseCase
import com.example.topacademy_android.main.domain.use_case.ValidateLoginUseCase
import com.example.topacademy_android.main.domain.validator.LoginValidator
import org.koin.dsl.module

val domainModule = module {
    factory { CalculatorUseCase(get()) }

    // Валидация логина
    factory { LoginValidator(get(), get()) }
    factory { ValidateLoginUseCase(get()) }
}