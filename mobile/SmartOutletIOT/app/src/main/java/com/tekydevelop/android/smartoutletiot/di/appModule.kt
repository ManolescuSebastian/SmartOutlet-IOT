package com.tekydevelop.android.smartoutletiot.di

import com.tekydevelop.android.smartoutletiot.presentation.adddevice.DeviceViewModel
import com.tekydevelop.android.smartoutletiot.presentation.gateway.GatewayViewModel
import com.tekydevelop.android.smartoutletiot.presentation.overview.OverviewViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { OverviewViewModel(get(), get()) }
    viewModel { GatewayViewModel(get()) }
    viewModel { DeviceViewModel(get()) }
}