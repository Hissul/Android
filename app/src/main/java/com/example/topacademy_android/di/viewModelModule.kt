package com.example.topacademy_android.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.example.topacademy_android.calculator.presentation.viewmodel.CalculatorViewModel

val viewModelModule = module {
    viewModel { CalculatorViewModel(get()) }
}