package com.example.coinapp

import android.app.Application
import androidx.activity.ComponentActivity
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    lateinit var activity: ComponentActivity
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MyApp
    }
}