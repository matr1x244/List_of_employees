package com.geekbrains.listofemployees

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.geekbrains.listofemployees.di.module.koin.appModuleKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this // Room

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModuleKoin)
        }
    }

    /**
     * Room
     */
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var appInstance: Context
    }
}
