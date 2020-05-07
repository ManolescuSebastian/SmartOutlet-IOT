package com.tekydevelop.android.smartoutletiot.di

import com.tekydevelop.android.smartoutletiot.domain.usecase.DeviceCases
import com.tekydevelop.android.smartoutletiot.domain.usecase.GatewayCase
import com.tekydevelop.android.smartoutletiot.domain.usecase.OverviewCase
import org.koin.dsl.module

val domainModule = module {

    single { OverviewCase(get()) }
    single { GatewayCase(get()) }
    single { DeviceCases(get()) }

}