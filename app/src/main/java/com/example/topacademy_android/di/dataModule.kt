package com.example.topacademy_android.di

import com.example.topacademy_android.calculator.domain.repository.CalculatorRepository
import com.example.topacademy_android.calculator.data.CalculatorRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<CalculatorRepository> { CalculatorRepositoryImpl() }
}