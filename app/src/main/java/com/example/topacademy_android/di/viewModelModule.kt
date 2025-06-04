package com.example.topacademy_android.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.example.topacademy_android.calculator.presentation.viewmodel.CalculatorViewModel
import com.example.topacademy_android.main.presentation.view_model.LoginViewModel
import com.example.topacademy_android.second.presentation.view_model.SecondViewModel

val viewModelModule = module {
    viewModel { CalculatorViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { SecondViewModel() }
}

