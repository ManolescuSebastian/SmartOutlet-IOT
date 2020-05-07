package com.tekydevelop.android.smartoutletiot.data.repository

import com.tekydevelop.android.smartoutletiot.data.mapper.mapNetworkErrors
import com.tekydevelop.android.smartoutletiot.data.model.DeviceRequest
import com.tekydevelop.android.smartoutletiot.data.model.Response
import com.tekydevelop.android.smartoutletiot.data.service.DeviceService
import com.tekydevelop.android.smartoutletiot.domain.model.DeviceData
import com.tekydevelop.android.smartoutletiot.domain.repository.DeviceRepository
import io.reactivex.Single

class DeviceRepositoryImpl(private val deviceService: DeviceService) : DeviceRepository {

    override fun availableDevices(): Single<List<DeviceData>> {
        return deviceService.availableDevices()
            .map { it.asDomain() }
            .mapNetworkErrors()
    }

    override fun addCase(id: Int, uuid: Int, name: String, type: Int): Single<Response> {
        return deviceService.addDevice(DeviceRequest(id, uuid, name, type))
            .mapNetworkErrors()
    }

    override fun editCase(id: Int, uuid: Int, name: String, type: Int): Single<Response> {
        return deviceService.editDevice(DeviceRequest(id, uuid, name, type))
            .mapNetworkErrors()
    }

    override fun deleteCase(id: Int): Single<Response> {
        return deviceService.deleteDevice(id)
            .mapNetworkErrors()
    }
}