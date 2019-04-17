package com.volkovmedia.supermunchkin

import android.app.Application
import org.koin.android.ext.android.startKoin

class MunchkinApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            androidContext = this,
            modules = koinModules
        )
    }

}