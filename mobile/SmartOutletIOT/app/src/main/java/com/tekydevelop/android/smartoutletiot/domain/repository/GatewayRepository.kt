package com.tekydevelop.android.smartoutletiot.domain.repository

import com.tekydevelop.android.smartoutletiot.data.model.GatewayPinResponse
import com.tekydevelop.android.smartoutletiot.data.model.Response
import io.reactivex.Single

interface GatewayRepository {
    /**
     * Check if gateway connection is established
     */
    fun checkGatewayStatus(): Single<Response>

    /**
     * Change RF transmitter gateway pin
     */
    fun setGatewayPinRF(pin: Int): Single<GatewayPinResponse>
}