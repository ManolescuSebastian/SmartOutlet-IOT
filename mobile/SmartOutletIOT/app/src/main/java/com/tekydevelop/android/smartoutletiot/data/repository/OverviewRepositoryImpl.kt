package com.tekydevelop.android.smartoutletiot.data.repository

import com.tekydevelop.android.smartoutletiot.data.model.DeviceRequest
import com.tekydevelop.android.smartoutletiot.data.model.OutletResponse
import com.tekydevelop.android.smartoutletiot.data.service.DeviceService
import com.tekydevelop.android.smartoutletiot.domain.repository.OverviewRepository
import io.reactivex.Single

class OverviewRepositoryImpl(private val deviceService: DeviceService) : OverviewRepository {

    override fun outletStatus(
        status: Boolean,
        id: Int,
        uuid: Int,
        name: String,
        type: Int
    ): Single<OutletResponse> {
        return deviceService.deviceStatus(status, DeviceRequest(id, uuid, name, type))
    }
}