package com.tekydevelop.android.smartoutletiot.domain.usecase

import com.tekydevelop.android.smartoutletiot.data.model.StatusResponse
import com.tekydevelop.android.smartoutletiot.domain.model.DeviceData
import com.tekydevelop.android.smartoutletiot.domain.repository.DeviceRepository
import io.reactivex.Single

class DeviceCases(private val deviceRepository: DeviceRepository) {

    fun executeAvailableDevices(): Single<List<DeviceData>> {
        return deviceRepository.availableDevices()
    }

    fun executeAddCase(device: DeviceData): Single<StatusResponse> {
        return deviceRepository.addCase(device.id, device.uuid, device.name, device.type)
    }

    fun executeEditCase(device: DeviceData): Single<StatusResponse> {
        return deviceRepository.editCase(device.id, device.uuid, device.name, device.type)
    }

    fun executeDeleteCase(id: Int): Single<StatusResponse> {
        return deviceRepository.deleteCase(id)
    }

}