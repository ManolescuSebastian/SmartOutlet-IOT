package com.tekydevelop.android.smartoutletiot.data.model

import com.google.gson.annotations.SerializedName
import com.tekydevelop.android.smartoutletiot.data.mapper.DomainMappableList
import com.tekydevelop.android.smartoutletiot.domain.model.DeviceData

data class DeviceDataResponse(
    val status: String,
    @SerializedName("data") val data: List<DeviceResponse>
) : DomainMappableList<DeviceData>{
    override fun asDomain(): List<DeviceData> {
        val deviceList: MutableList<DeviceData> = mutableListOf()
        data.forEach {
            deviceList.add(it.asDomain())
        }
        return deviceList
    }
}