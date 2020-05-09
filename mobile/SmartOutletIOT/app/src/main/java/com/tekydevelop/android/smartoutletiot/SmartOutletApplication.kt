package com.tekydevelop.android.smartoutletiot

import android.app.Application
import com.tekydevelop.android.smartoutletiot.di.appModule
import com.tekydevelop.android.smartoutletiot.di.dataModule
import com.tekydevelop.android.smartoutletiot.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SmartOutletApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SmartOutletApplication)
            modules(
                listOf(
                    appModule,
                    dataModule,
                    domainModule
                )
            )
        }
    }
}