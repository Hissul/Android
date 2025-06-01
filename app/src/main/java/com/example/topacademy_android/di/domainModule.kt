package com.example.topacademy_android.di

import com.example.topacademy_android.calculator.domain.use_case.CalculatorUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { CalculatorUseCase(get()) }
}