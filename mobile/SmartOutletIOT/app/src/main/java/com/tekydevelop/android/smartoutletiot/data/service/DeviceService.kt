package com.tekydevelop.android.smartoutletiot.data.service

import com.tekydevelop.android.smartoutletiot.data.model.DeviceDataResponse
import com.tekydevelop.android.smartoutletiot.data.model.DeviceRequest
import com.tekydevelop.android.smartoutletiot.data.model.OutletResponse
import com.tekydevelop.android.smartoutletiot.data.model.Response
import io.reactivex.Single
import retrofit2.http.*

interface DeviceService {

    @POST("api/control")
    fun deviceStatus(@Query("state") status: Boolean, @Body deviceRequest: DeviceRequest): Single<OutletResponse>

    @GET("api/device")
    fun availableDevices(): Single<DeviceDataResponse>

    @POST("api/device")
    fun addDevice(@Body DeviceRequest: DeviceRequest): Single<Response>

    @PUT("api/device")
    fun editDevice(@Body DeviceRequest: DeviceRequest): Single<Response>

    @DELETE("api/device")
    fun deleteDevice(@Query("id") id: Int): Single<Response>
}