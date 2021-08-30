package com.cindy.cibweatherapp.ui

import com.cindy.cibweatherapp.data.CoroutineContextProvider
import com.cindy.cibweatherapp.api.WeatherApiService
import com.cindy.cibweatherapp.base.BaseViewModel
import com.cindy.cibweatherapp.data.DailyForecast
import com.cindy.cibweatherapp.data.WeatherRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private var service: WeatherApiService,
    private var weatherRepository: WeatherRepository
) : BaseViewModel(contextProvider) {

    suspend fun getDailyForecast(
        lat: Double,
        lon: Double,
        days: Int,
        appKey: String
    ): DailyForecast? {
        val dailyForecast = weatherRepository.getDailyForecast(lat, lon, days, appKey)
        return handleWeatherApiResponse(dailyForecast)
    }
}