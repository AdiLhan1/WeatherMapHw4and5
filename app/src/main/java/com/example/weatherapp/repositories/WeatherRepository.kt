package com.example.weatherapp.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.MainWeatherModel
import com.example.weatherapp.network.ApiService
import com.example.weatherapp.network.RetrofitClient
import com.example.weatherapp.network.WEATHER_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val BASE_URL = "http://api.openweathermap.org/"
class WeatherRepository {
    private lateinit var api: ApiService
    fun getWeatherData(units: String?, lat: Double?, lon: Double?): LiveData<MainWeatherModel> {
        api = RetrofitClient.instanceRetrofit(BASE_URL)!!
        val data = MutableLiveData<MainWeatherModel>()
        api.getWeatherData(units, lat, lon, WEATHER_KEY)
            .enqueue(object : Callback<MainWeatherModel> {
                override fun onResponse(
                    call: Call<MainWeatherModel>?,
                    response: Response<MainWeatherModel>?
                ) {
                    Log.e("-------", "$response")
                    data.value = response?.body()
                }

                override fun onFailure(call: Call<MainWeatherModel>?, t: Throwable?) {
                    data.value = null
                }
            })
        return data
    }

}