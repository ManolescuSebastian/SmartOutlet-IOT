package com.tekydevelop.android.smartoutletiot.domain.usecase

import com.tekydevelop.android.smartoutletiot.data.model.StatusResponse
import com.tekydevelop.android.smartoutletiot.domain.repository.GatewayRepository
import io.reactivex.Single

class GatewayCase(private val gatewayRepository: GatewayRepository) {

    fun execute(): Single<StatusResponse> {
       return gatewayRepository.checkGatewayStatus()
    }

}