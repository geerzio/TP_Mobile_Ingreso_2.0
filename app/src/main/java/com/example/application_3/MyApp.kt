package com.example.application_3

import android.app.Application

class MyApp: Application() {

    //val database: MyApp by lazy { this }

    companion object {
        lateinit var instance: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}