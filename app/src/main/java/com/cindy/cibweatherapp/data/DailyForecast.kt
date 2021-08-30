package com.cindy.cibweatherapp.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DailyForecast(
    @SerializedName("data") val list: List<ForecastItem>,
    @SerializedName("city_name") val city: String
)

data class ForecastItem(
    @SerializedName("weather") val weather: WeatherObject,
    @SerializedName("valid_date") val day: String,
    @SerializedName("max_temp") val maxTemp: Double,
    @SerializedName("min_temp") val minTemp: Double
)

data class WeatherObject(
    @SerializedName("icon") val icon: String,
    @SerializedName("description") val description: String
)