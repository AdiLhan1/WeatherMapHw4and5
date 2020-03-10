package com.example.weatherapp.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.MainWeatherModel
import com.example.weatherapp.repositories.WeatherRepository

class MapViewModel(private val wRepository: WeatherRepository) : ViewModel() {

    fun getWeatherData(units: String, lat: Double, lon: Double): LiveData<MainWeatherModel> {
        return wRepository.getWeatherData(units, lat, lon)
    }

}

