package com.cindy.cibweatherapp.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cindy.cibweatherapp.R

class HourlyWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = HourlyWeatherFragment()
    }

    private lateinit var viewModel: HourlyWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hourly_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HourlyWeatherViewModel::class.java)
        // TODO: Use the ViewModel
    }

}