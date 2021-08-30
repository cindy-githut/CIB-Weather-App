package com.cindy.cibweatherapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.cindy.cibweatherapp.data.CoroutineContextProvider
import com.cindy.cibweatherapp.api.WeatherApiError
import com.cindy.cibweatherapp.api.WeatherApiResponse
import kotlinx.coroutines.channels.Channel

abstract class BaseViewModel(contextProvider: CoroutineContextProvider) : ViewModel() {

    private val weatherErrorChannel = Channel<WeatherApiError>()
    private val exceptionChannel = Channel<Exception?>()
    private val networkChannel = Channel<Unit>()

    val weatherError = liveData(contextProvider.iO) {
        for (error in weatherErrorChannel) emit(error.message)
    }

    val exception = liveData(contextProvider.iO) {
        for (error in exceptionChannel) emit(error?.message)
    }

    val networkError = liveData(contextProvider.iO) {
        for (error in networkChannel) emit(error)
    }

    protected suspend fun <T : Any> handleWeatherApiResponse(weatherApiResponse: WeatherApiResponse<T>): T? {
        return when (weatherApiResponse) {
            is WeatherApiResponse.NetworkError -> {
                networkChannel.send(Unit)
                return null
            }
            is WeatherApiResponse.Success -> {
                weatherApiResponse.data
            }
            is WeatherApiResponse.WeatherError -> {
                weatherErrorChannel.send(weatherApiResponse.weatherApiError)
                return null
            }
            is WeatherApiResponse.Error -> {
                exceptionChannel.send(weatherApiResponse.exception)
                return null
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        weatherErrorChannel.cancel()
        exceptionChannel.cancel()
        networkChannel.cancel()
    }
}
