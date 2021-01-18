package com.example.petgalleryapp

import android.app.Application
import androidx.fragment.app.Fragment
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class PGApplication : Application() {

    companion object {
        lateinit var instance: PGApplication private set
        val context get() = instance.applicationContext
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}