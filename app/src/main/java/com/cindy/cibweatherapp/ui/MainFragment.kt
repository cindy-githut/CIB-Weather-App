package com.cindy.cibweatherapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.cindy.cibweatherapp.BuildConfig
import com.cindy.cibweatherapp.WeatherApp
import com.cindy.cibweatherapp.base.BaseFragment
import com.cindy.cibweatherapp.databinding.MainFragmentBinding
import com.cindy.cibweatherapp.utils.ActivityLifeCycleObserver
import kotlinx.coroutines.launch

class MainFragment : BaseFragment() {


    private var fragmentBinding: MainFragmentBinding? = null

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
        fragmentBinding = MainFragmentBinding.inflate(inflater, container, false)

        initViews()
        getDailyForecast(-33.8423238, 18.547371, 7, BuildConfig.APP_KEY)

        onErrorEvents()

        return binding.root
    }

    private fun initViews() {


    }

    private fun getDailyForecast(lat: Double, lon: Double, days: Int, appId: String) {
        lifecycleScope.launch {

            val dailyForecastList = viewModel.getDailyForecast(lat, lon, days, appId)

            if (dailyForecastList != null) {

                dailyForecastList.city.let {
                    binding.currentLocation.text = it
                }

                if(dailyForecastList.list.isNotEmpty()){
                    binding.weatherForecastRecyclerView.adapter = ForecastItemsListAdapter(dailyForecastList.list)
                }

            }
        }
    }

}