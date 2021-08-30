package com.cindy.cibweatherapp.di

import com.cindy.cibweatherapp.ui.MainActivity
import com.cindy.cibweatherapp.ui.MainFragment
import com.cindy.cibweatherapp.WeatherApp
import com.cindy.cibweatherapp.base.BaseFragment
import com.cindy.cibweatherapp.ui.HourlyWeatherFragment

interface MainVariantComponent {

    fun inject(target: WeatherApp)

    fun inject(target: MainActivity)

    fun inject(target: MainFragment)

    fun inject(target: BaseFragment)

    fun inject(target: HourlyWeatherFragment)

    interface Creator<T> {
        fun create(weatherApp: WeatherApp): T
    }
}
