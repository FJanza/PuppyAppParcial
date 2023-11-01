package com.example.puppyappparcial

import android.app.Application
import com.example.puppyappparcial.core.Configuration

class PuppyApp:Application() {

    override fun onCreate() {
        super.onCreate()

        Configuration.baseUrl = resources.getString(R.string.dog_api_base_url)
    }
}