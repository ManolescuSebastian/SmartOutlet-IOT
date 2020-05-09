package com.tekydevelop.android.smartoutletiot.domain.usecase

import com.tekydevelop.android.smartoutletiot.data.model.OutletResponse
import com.tekydevelop.android.smartoutletiot.domain.model.DeviceData
import com.tekydevelop.android.smartoutletiot.domain.repository.OverviewRepository
import io.reactivex.Single

class OverviewCase(private val overviewRepository: OverviewRepository) {

    fun execute(params: Params): Single<OutletResponse> {
        return overviewRepository.outletStatus(
            params.status,
            params.device.id,
            params.device.uuid,
            params.device.name,
            params.device.type
        )
    }

    data class Params(val status: Boolean, val device: DeviceData)

}