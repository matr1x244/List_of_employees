package com.geekbrains.listofemployees

import android.app.Application
import com.geekbrains.listofemployees.di.module.koin.appModuleKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModuleKoin)
        }
    }

}
