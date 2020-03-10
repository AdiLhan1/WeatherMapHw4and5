package com.example.weatherapp.network

import com.example.weatherapp.model.MainWeatherModel
import com.example.weatherapp.model.city.CityDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val WEATHER_KEY = "c6e381d8c7ff98f0fee43775817cf6ad"

interface ApiService {

    @GET("data/2.5/weather")
    fun getWeatherData(
        @Query("units") units: String?,
        @Query("lat") lat: Double?,
        @Query("lon") lon: Double?,
        @Query("appId") appId: String?
    ): Call<MainWeatherModel>
    @GET("rest/v2/capital/{city}")
    fun getCityData(@Path("city") capital: String) : Call<List<CityDataModel>>
}
