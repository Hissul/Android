package com.example.topacademy_android


import android.app.Application
import com.example.topacademy_android.di.carModule
import com.example.topacademy_android.di.dataModule
import com.example.topacademy_android.di.domainModule
import com.example.topacademy_android.di.presentationModule
import com.example.topacademy_android.di.viewModelModule
import com.example.topacademy_android.di.weatherModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(
                dataModule,
                domainModule,
                viewModelModule,
                presentationModule,
                weatherModule,
                carModule
            )
        }
    }
}