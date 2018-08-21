package com.readboy.ibbasenetwork

import android.app.Application

class NetworkLibraryApp: Application() {

    companion object {
        lateinit var INSTANCE: NetworkLibraryApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}