package com.example.weatherapp

import android.annotation.SuppressLint
import android.app.Application
import com.example.weatherapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
@SuppressLint("Registered")
class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherApp)
            modules(appModule)
        }

    }
}