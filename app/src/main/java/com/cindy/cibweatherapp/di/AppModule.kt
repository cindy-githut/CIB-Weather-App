package com.cindy.cibweatherapp.di

import android.content.Context
import com.cindy.cibweatherapp.WeatherApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesApplicationContext(weatherApp: WeatherApp): Context = weatherApp

}