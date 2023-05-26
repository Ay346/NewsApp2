package com.example.newsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

import timber.log.Timber

@HiltAndroidApp
class NewsAppliction : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
//            make tag by default name is in currently screan
//            Timber.d("on click")
        }
    }
}