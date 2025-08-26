package com.example.bachelors

import android.app.Application
import com.bachelors.authsdk.appSdkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MyApp)
//            listOf(appModule, appSdkModule)
            modules(appModule)
            modules(appSdkModule)
        }
    }
}