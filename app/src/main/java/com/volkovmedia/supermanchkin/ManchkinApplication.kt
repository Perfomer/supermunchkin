package com.volkovmedia.supermanchkin

import android.app.Application
import org.koin.android.ext.android.startKoin

class ManchkinApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            androidContext = this,
            modules = koinModules
        )
    }

}