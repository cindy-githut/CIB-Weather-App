package com.cindy.cibweatherapp.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cindy.cibweatherapp.api.WeatherApiService
import com.cindy.cibweatherapp.ui.MainViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainViewModelFactory @Inject constructor(
    private val contextProvider: AppCoroutineContextProvider,
    private var service: WeatherApiService,
    private var weatherRepository: WeatherRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(contextProvider, service, weatherRepository) as T
    }
}

class AppCoroutineContextProvider @Inject constructor() : CoroutineContextProvider {
    override val iO by lazy { Dispatchers.IO }
}

interface CoroutineContextProvider {
    val iO: CoroutineContext
}

