package com.example.weatherapp.model.city

import com.google.gson.annotations.SerializedName

data class LanguagesItem(@SerializedName("nativeName")
                         val nativeName: String? = "",
                         @SerializedName("iso639_2")
                         val iso639_2: String? = "",
                         @SerializedName("name")
                         val name: String? = "",
                         @SerializedName("iso639_1")
                         val iso639_1: String? = "")