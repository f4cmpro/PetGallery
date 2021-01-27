package com.example.petgalleryapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class PGApplication : Application() {

    companion object {
        lateinit var instance: PGApplication private set
        val context: Context get() = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}