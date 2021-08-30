package com.cindy.cibweatherapp.data

import com.cindy.cibweatherapp.api.WeatherApiResponse
import com.cindy.cibweatherapp.api.WeatherApiService
import javax.inject.Inject

interface WeatherRepository {

    suspend fun getDailyForecast(
        lat: Double,
        lon: Double,
        days: Int,
        appKey: String
    ): WeatherApiResponse<DailyForecast>

}

class WeatherRepositoryImpl @Inject constructor(
    private val service: WeatherApiService
) : BaseRepository(), WeatherRepository {
    override suspend fun getDailyForecast(lat: Double, lon: Double, days: Int, appKey: String) =
        safeApiCall { service.getDailyForecast(lat, lon, days, appKey) }

}
