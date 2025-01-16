package com.example.appnotes_4m

import android.app.Application

class App() : Application(){

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceHelper()
        sharedPreferences.unit(this)
    }
}