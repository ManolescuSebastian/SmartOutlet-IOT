package com.tekydevelop.android.smartoutletiot.common

import okhttp3.Interceptor
import okhttp3.Response

class DefaultInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("Content-Type", "application/json")

        return chain.proceed(request.build())
    }
}