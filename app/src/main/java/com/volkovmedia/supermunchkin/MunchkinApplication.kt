package com.volkovmedia.supermunchkin

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.android.startKoin

@Suppress("unused")
class MunchkinApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            androidContext = this,
            modules = koinModules
        )

        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
    }

}