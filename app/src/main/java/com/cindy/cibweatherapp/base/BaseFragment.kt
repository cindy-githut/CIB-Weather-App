package com.cindy.cibweatherapp.base

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.cindy.cibweatherapp.ui.MainViewModel
import com.cindy.cibweatherapp.data.MainViewModelFactory
import com.cindy.cibweatherapp.WeatherApp
import javax.inject.Inject

abstract class BaseFragment : Fragment() {


    val viewModel: MainViewModel by viewModels { mainViewModelFactory }

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    protected val navController: NavController
        get() = findNavController()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        WeatherApp.getComponent(context).inject(this)

    }

    private fun showMessage(toastMessage: String) {
        Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_LONG).show()
    }

    fun onErrorEvents() {

        viewModel.exception.observe(viewLifecycleOwner, { exception ->
            when {
                exception != null -> {
                    showMessage(exception.toString())
                }
            }
        })

        viewModel.networkError.observe(viewLifecycleOwner, { networkError ->
            when {
                networkError != null -> {
                    showMessage("NO CONNECTION")
                }
            }
        })

        viewModel.weatherError.observe(viewLifecycleOwner, { weatherError ->
            when {
                weatherError != null -> {
                    showMessage(weatherError.toString())
                }
            }
        })
    }
}
