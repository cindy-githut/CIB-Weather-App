package com.cindy.cibweatherapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.cindy.cibweatherapp.R
import com.cindy.cibweatherapp.WeatherApp
import com.cindy.cibweatherapp.databinding.HourlyWeatherFragmentBinding
import com.cindy.cibweatherapp.utils.ActivityLifeCycleObserver
import com.cindy.cibweatherapp.utils.getDayOfWeek
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class HourlyWeatherFragment : Fragment() {

    private val args: HourlyWeatherFragmentArgs by navArgs()

    private var fragmentBinding: HourlyWeatherFragmentBinding? = null

    private val binding get() = fragmentBinding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)

        WeatherApp.getComponent(context).inject(this)

        requireActivity().lifecycle.addObserver(ActivityLifeCycleObserver {})
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentBinding = HourlyWeatherFragmentBinding.inflate(inflater, container, false)

        initViews()

        return binding.root
    }

    private fun initViews() {

        val forecastItem = args.forecastItem

        val imageIcon = forecastItem.weather.icon
        val temperatureIcon = "https://www.weatherbit.io/static/img/icons/$imageIcon.png"
        val picasso = Picasso.get()

        picasso.load(temperatureIcon)
            .into(binding.forecastIcon)

        binding.highTemperature.text = getString(
            R.string.high_temp,
            displayInCelsiusDegrees(forecastItem.highTemp.roundToInt())
        )
        binding.clouds.text = getString(R.string.clouds, forecastItem.clouds).plus("%")
        binding.winSpeed.text = getString(
            R.string.wind_speed,
            String.format("%.2f", forecastItem.windSpd)
        )
        binding.minTemperature.text = getString(
            R.string.minTemperature,
            displayInCelsiusDegrees(forecastItem.minTemp.roundToInt())
        )
        binding.maxTemperature.text = getString(
            R.string.maxTemperature,
            displayInCelsiusDegrees(forecastItem.maxTemp.roundToInt())
        )
        binding.day.text = forecastItem.day.getDayOfWeek()
    }

    private fun displayInCelsiusDegrees(temperature: Int): String {
        return "$temperature \u2103"
    }
}