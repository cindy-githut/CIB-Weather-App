package com.cindy.cibweatherapp.data

import com.cindy.cibweatherapp.api.WeatherApiError
import com.cindy.cibweatherapp.api.WeatherApiResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseRepository {
    suspend fun <T: Any> safeApiCall(call: suspend () -> Response<T>): WeatherApiResponse<T> {

        return withContext(Dispatchers.IO) {
            try {
                val response = call.invoke()
                when {
                    response.isSuccessful -> {
                        WeatherApiResponse.Success(response.body()!!)
                    }
                    response.code() > 400 -> WeatherApiResponse.WeatherError(Gson().fromJson(response.errorBody().toString(), WeatherApiError::class.java))
                    else -> WeatherApiResponse.WeatherError(Gson().fromJson(response.errorBody().toString(), WeatherApiError::class.java))
                }
            } catch (throwable: Throwable) {
                WeatherApiResponse.WeatherError(WeatherApiError("",throwable.message.toString()))
            }
        }
    }
}