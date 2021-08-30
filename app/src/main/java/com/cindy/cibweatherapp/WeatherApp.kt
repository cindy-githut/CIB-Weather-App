package com.cindy.cibweatherapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.ProcessLifecycleOwner
import com.cindy.cibweatherapp.di.VariantComponent
import javax.inject.Inject


class WeatherApp @Inject constructor(): Application() {

    lateinit var component: VariantComponent private set

    override fun onCreate() {
        super.onCreate()

        component = VariantComponent.create(this)
        component.inject(this)

//        ProcessLifecycleOwner.get().lifecycle.addObserver(appLifecycleObserver)


    }

    companion object {

        /**
         * Get the {@link #WeatherApp} instance of this running application from a given
         * input context
         *
         * @param context required
         */

        fun get(context: Context) = context.applicationContext as WeatherApp

        /**
         * Get the {@link WeatherApp#VariantComponent} for dagger injections. This is a simple
         * convenience method instead of using {@link WeatherApp#get} and then referencing
         * the component.
         *
         * @param context required
         */
        fun getComponent(context: Context) = get(context).component

    }
}
