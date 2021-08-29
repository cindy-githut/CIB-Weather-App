package com.cindy.cibweatherapp.di

import com.cindy.cibweatherapp.WeatherApp
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface VariantComponent : MainVariantComponent {

    companion object Creator : MainVariantComponent.Creator<VariantComponent> {
        override fun create(weatherApp: WeatherApp): VariantComponent {
            return DaggerVariantComponent.builder()
                .application(weatherApp)
                .build()
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(weatherApp: WeatherApp): Builder

        fun build(): VariantComponent
    }
}
