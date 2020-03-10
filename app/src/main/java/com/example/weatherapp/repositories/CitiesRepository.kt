package com.example.weatherapp.repositories

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.city.CityDataModel
import com.example.weatherapp.network.ApiService
import com.example.weatherapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val BASE_CITY_URL = "https://restcountries.eu/"
class CitiesRepository() {
    private lateinit var api: ApiService

    fun getCityData(capital: String): MutableLiveData<List<CityDataModel>> {
        api = RetrofitClient.instanceRetrofit(BASE_CITY_URL)!!
        val data = MutableLiveData<List<CityDataModel>>()
        api.getCityData(capital)
            .enqueue(object : Callback<List<CityDataModel>> {
                override fun onResponse(
                    call: Call<List<CityDataModel>>,
                    response: Response<List<CityDataModel>>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(call: Call<List<CityDataModel>>, t: Throwable) {
                    data.value = null
                }
            })
        return data
    }

}