package com.tekydevelop.android.smartoutletiot.domain.repository

import com.tekydevelop.android.smartoutletiot.data.model.StatusResponse
import com.tekydevelop.android.smartoutletiot.domain.model.DeviceData
import io.reactivex.Single

interface DeviceRepository {

    /**
     * Get all available devices from gateway
     */
    fun availableDevices(): Single<List<DeviceData>>

    /**
     * Add new device
     */
    fun addCase(id: Int, uuid: Int, name: String, type: Int): Single<StatusResponse>

    /**
     * Edit selected device
     */
    fun editCase(id: Int, uuid: Int, name: String, type: Int): Single<StatusResponse>

    /**
     * Delete device by id
     */
    fun deleteCase(id: Int): Single<StatusResponse>

}