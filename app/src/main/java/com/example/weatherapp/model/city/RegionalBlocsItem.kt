package com.example.weatherapp.model.city

import com.google.gson.annotations.SerializedName

data class RegionalBlocsItem(@SerializedName("acronym")
                             val acronym: String? = "",
                             @SerializedName("name")
                             val name: String? = "")