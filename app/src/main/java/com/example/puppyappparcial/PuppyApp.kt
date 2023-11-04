package com.example.puppyappparcial

import android.app.Application
import com.example.puppyappparcial.core.Configuration
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PuppyApp:Application() {


    override fun onCreate() {
        super.onCreate()

        Configuration.baseUrl = resources.getString(R.string.dog_api_base_url)
    }
}


