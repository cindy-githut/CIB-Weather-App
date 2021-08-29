package com.cindy.cibweatherapp.di

import com.cindy.cibweatherapp.WeatherApp

interface MainVariantComponent {

    fun inject(target: WeatherApp)

    interface Creator<T> {
        fun create(weatherApp: WeatherApp): T
    }
}
