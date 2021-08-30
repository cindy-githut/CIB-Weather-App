package com.cindy.cibweatherapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.cindy.cibweatherapp.R
import com.cindy.cibweatherapp.data.ForecastItem
import com.cindy.cibweatherapp.utils.getDayOfWeek
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class ForecastItemsListAdapter(private val items: List<ForecastItem>,
                               private val onItemClickListener: OnItemClickedListener
) :
    RecyclerView.Adapter<ForecastItemsListAdapter.ForecastItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ForecastItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_forecast_items_iem, parent, false)
        return ForecastItemViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ForecastItemViewHolder, position: Int) {

        when {
            items.isNotEmpty() -> {
                val forecastItem = items[position]

                holder.bind(forecastItem)
            }
        }
    }

    inner class ForecastItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var day: AppCompatTextView = view.findViewById(R.id.day)
        var forecastIcon: AppCompatImageView = view.findViewById(R.id.forecastIcon)
        var forecastDescription: AppCompatTextView = view.findViewById(R.id.forecastDescription)
        var forecastTemperature: AppCompatTextView = view.findViewById(R.id.forecastTemperature)

        fun bind(forecastItem: ForecastItem) {

            day.text = forecastItem.day.getDayOfWeek()

            forecastItem.weather.description.let {
                forecastDescription.text = it
            }

            forecastItem.weather.icon.let {
                if (it.isNotBlank()) {

                    val temperatureIcon = "https://www.weatherbit.io/static/img/icons/$it.png"
                    val picasso = Picasso.get()

                    picasso.load(temperatureIcon)
                        .into(forecastIcon)

                }
            }

            val minTemp = forecastItem.minTemp.roundToInt()
            val maxTemp = forecastItem.maxTemp.roundToInt()

            val minTempCelsiusDegrees = displayInCelsiusDegrees(minTemp)
            val maxTempCelsiusDegrees = displayInCelsiusDegrees(maxTemp)

            forecastItem.maxTemp.let {
                forecastTemperature.text = forecastTemperature.context.getString(
                    R.string.temperature,
                    maxTempCelsiusDegrees,
                    minTempCelsiusDegrees
                )
            }

            itemView.let {
                it.setOnClickListener {
                    onItemClickListener.onItemClicked(forecastItem)
                }
            }
        }

        private fun displayInCelsiusDegrees(temperature: Int): String {
            return "$temperature \u2103"
        }
    }

    interface OnItemClickedListener {
        fun onItemClicked(forecastItem: ForecastItem)
    }

}
