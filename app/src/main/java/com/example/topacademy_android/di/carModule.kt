package com.example.topacademy_android.di

import com.example.topacademy_android.car.presentation.adapter.CarAdapter
import com.example.topacademy_android.car.domain.model.Car
import org.koin.dsl.module

val carModule = module {

    // Моковые данные (в будущем можно будет заменить на репозиторий)
    single<List<Car>> {
        listOf(
            Car("BMW", "X5", 2020, "Luxury SUV", 55000, com.example.topacademy_android.R.drawable.ic_launcher_foreground),
            Car("Audi", "A4", 2019, "Sedan with quattro", 40000, com.example.topacademy_android.R.drawable.ic_launcher_foreground),
            Car("Toyota", "Camry", 2021, "Reliable car", 30000, com.example.topacademy_android.R.drawable.ic_launcher_foreground),
            Car("Tesla", "Model S", 2022, "Electric performance", 80000, com.example.topacademy_android.R.drawable.ic_launcher_foreground)
        )
    }

    // Адаптер, зависящий от списка машин
    factory { CarAdapter(get()) }
}