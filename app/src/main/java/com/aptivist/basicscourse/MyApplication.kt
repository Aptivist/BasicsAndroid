package com.aptivist.basicscourse

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import androidx.core.content.ContextCompat

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        println(" APPLICATION WAS CONFIGURED!!! ")
    }

}