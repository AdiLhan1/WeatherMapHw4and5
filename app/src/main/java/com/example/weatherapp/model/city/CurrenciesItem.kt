package com.example.weatherapp.model.city

import com.google.gson.annotations.SerializedName

data class CurrenciesItem(@SerializedName("symbol")
                          val symbol: String? = "",
                          @SerializedName("code")
                          val code: String? = "",
                          @SerializedName("name")
                          val name: String? = "")