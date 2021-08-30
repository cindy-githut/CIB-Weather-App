package com.cindy.cibweatherapp.data

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
data class DailyForecast(
    @SerializedName("data") val list: List<ForecastItem>,
    @SerializedName("city_name") val city: String
)

@Parcelize
data class ForecastItem(
    @SerializedName("weather") val weather: WeatherObject,
    @SerializedName("valid_date") val day: String,
    @SerializedName("max_temp") val maxTemp: Double,
    @SerializedName("min_temp") val minTemp: Double,
    @SerializedName("high_temp") val highTemp: Double,
    @SerializedName("wind_spd") val windSpd: Double,
    @SerializedName("clouds") val clouds: Int,

): Parcelable

@Parcelize
data class WeatherObject(
    @SerializedName("icon") val icon: String,
    @SerializedName("description") val description: String
): Parcelable