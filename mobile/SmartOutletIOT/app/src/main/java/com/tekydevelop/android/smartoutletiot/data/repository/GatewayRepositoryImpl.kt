package com.tekydevelop.android.smartoutletiot.data.repository

import com.tekydevelop.android.smartoutletiot.data.model.GatewayPinResponse
import com.tekydevelop.android.smartoutletiot.data.model.StatusResponse
import com.tekydevelop.android.smartoutletiot.data.service.GatewayService
import com.tekydevelop.android.smartoutletiot.domain.repository.GatewayRepository
import io.reactivex.Single

class GatewayRepositoryImpl(private val gatewayService: GatewayService) : GatewayRepository {
    override fun checkGatewayStatus(): Single<StatusResponse> {
        return gatewayService.checkGateway()
    }

    override fun setGatewayPinRF(pin: Int): Single<GatewayPinResponse> {
        return gatewayService.setGatewayRFPin(pin)
    }
}