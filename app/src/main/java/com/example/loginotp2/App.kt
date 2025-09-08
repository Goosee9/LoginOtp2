package com.example.loginotp2

import android.app.Application
import com.example.loginotp2.di.appModule
import com.example.loginotp2.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                appModule,
                viewModelModule
            )
        }
    }
}





