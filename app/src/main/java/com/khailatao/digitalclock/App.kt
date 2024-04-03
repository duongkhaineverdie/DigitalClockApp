package com.khailatao.digitalclock

import android.app.Application
import com.khailatao.digitalclock.di.dispatcherModule
import com.khailatao.digitalclock.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                viewModelModule,
                dispatcherModule
            )
        }
    }
}