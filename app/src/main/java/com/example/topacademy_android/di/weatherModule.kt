package com.example.topacademy_android.di

import com.example.topacademy_android.weather.data.remote.api.WeatherApi
import com.example.topacademy_android.weather.data.repository.WeatherRepositoryImpl
import com.example.topacademy_android.weather.domain.repository.WeatherRepository
import com.example.topacademy_android.weather.domain.usecase.GetWeatherUseCase
import com.example.topacademy_android.weather.presentation.viewmodel.WeatherViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val weatherModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://www.7timer.info/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build()
            )
            .build()
            .create(WeatherApi::class.java)
    }

    single<WeatherRepository> { WeatherRepositoryImpl(get()) }

    factory { GetWeatherUseCase(get()) }

    viewModel { WeatherViewModel(get()) }
}