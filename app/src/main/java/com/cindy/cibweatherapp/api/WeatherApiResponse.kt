package com.cindy.cibweatherapp.api

sealed class WeatherApiResponse<out T: Any> {
    data class Success<out T: Any>(val data: T): WeatherApiResponse<T>()
    data class WeatherError(val weatherApiError: WeatherApiError): WeatherApiResponse<Nothing>()
    data class Error(val exception: Exception): WeatherApiResponse<Nothing>()
    object NetworkError: WeatherApiResponse<Nothing>()
}