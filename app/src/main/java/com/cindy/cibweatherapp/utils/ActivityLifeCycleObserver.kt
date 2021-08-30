package com.cindy.cibweatherapp.utils

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class ActivityLifeCycleObserver(
    private val update: () -> Unit
) : DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        // Remove the LifecycleObserver once you get a callback to ON_CREATE
        owner.lifecycle.removeObserver(this)
        update()
    }
}

