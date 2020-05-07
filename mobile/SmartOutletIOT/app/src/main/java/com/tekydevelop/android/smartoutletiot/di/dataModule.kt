package com.tekydevelop.android.smartoutletiot.di

import com.tekydevelop.android.smartoutletiot.BuildConfig
import com.tekydevelop.android.smartoutletiot.common.DefaultInterceptor
import com.tekydevelop.android.smartoutletiot.data.repository.DeviceRepositoryImpl
import com.tekydevelop.android.smartoutletiot.data.repository.GatewayRepositoryImpl
import com.tekydevelop.android.smartoutletiot.data.repository.OverviewRepositoryImpl
import com.tekydevelop.android.smartoutletiot.data.service.DeviceService
import com.tekydevelop.android.smartoutletiot.data.service.GatewayService
import com.tekydevelop.android.smartoutletiot.domain.repository.DeviceRepository
import com.tekydevelop.android.smartoutletiot.domain.repository.GatewayRepository
import com.tekydevelop.android.smartoutletiot.domain.repository.OverviewRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

val dataModule = module {

    single<Interceptor> { DefaultInterceptor() }

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(interceptor = get())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_BASE_URL)
            .client(get<OkHttpClient>())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Repositories
    single<OverviewRepository> { OverviewRepositoryImpl(get()) }
    single<GatewayRepository> { GatewayRepositoryImpl(get()) }
    single<DeviceRepository> { DeviceRepositoryImpl(get()) }


    //Services
    single<DeviceService> { get<Retrofit>().create(DeviceService::class.java) }
    single<GatewayService> { get<Retrofit>().create(GatewayService::class.java) }
}