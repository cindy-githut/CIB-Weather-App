package com.cindy.cibweatherapp.di

import android.content.Context
import com.cindy.cibweatherapp.WeatherApp
import com.cindy.cibweatherapp.data.WeatherRepository
import com.cindy.cibweatherapp.data.WeatherRepositoryImpl
import com.cindy.cibweatherapp.api.WeatherApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesApplicationContext(weatherApp: WeatherApp): Context = weatherApp

    @Singleton
    @Provides
    fun providesWeatherRepository(service: WeatherApiService): WeatherRepository =
        WeatherRepositoryImpl(service)

}