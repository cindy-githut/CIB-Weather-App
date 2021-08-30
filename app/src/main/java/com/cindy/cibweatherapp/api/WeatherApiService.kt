package com.cindy.cibweatherapp.api

import com.cindy.cibweatherapp.data.DailyForecast
import com.cindy.cibweatherapp.data.ForecastItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiService {

    @GET("?")
    suspend fun getDailyForecast(@Query("lat") latitude: Double, @Query("lon") longitude: Double,
                                 @Query("days") days: Int, @Query("key") key: String): Response<DailyForecast>
}
