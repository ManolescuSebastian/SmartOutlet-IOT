package com.tekydevelop.android.smartoutletiot.data.service

import com.tekydevelop.android.smartoutletiot.data.model.GatewayPinResponse
import com.tekydevelop.android.smartoutletiot.data.model.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface GatewayService {

    @GET("api/gateway")
    fun checkGateway(): Single<Response>

    @POST("api/gateway")
    fun setGatewayRFPin(@Query("rfpin") rfPin: Int): Single<GatewayPinResponse>
}