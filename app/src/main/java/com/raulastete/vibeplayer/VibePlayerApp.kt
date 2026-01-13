package com.raulastete.vibeplayer

import android.app.Application
import com.raulastete.vibeplayer.di.appModule
import com.raulastete.vibeplayer.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class VibePlayerApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@VibePlayerApp)
            modules(appModule, homeModule)
        }
    }
}