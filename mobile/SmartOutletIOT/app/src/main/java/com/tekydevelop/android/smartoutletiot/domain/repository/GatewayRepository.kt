package com.tekydevelop.android.smartoutletiot.domain.repository

import com.tekydevelop.android.smartoutletiot.data.model.GatewayPinResponse
import com.tekydevelop.android.smartoutletiot.data.model.StatusResponse
import io.reactivex.Single

interface GatewayRepository {
    /**
     * Check if gateway connection is established
     */
    fun checkGatewayStatus(): Single<StatusResponse>

    /**
     * Change RF transmitter gateway pin
     */
    fun setGatewayPinRF(pin: Int): Single<GatewayPinResponse>
}