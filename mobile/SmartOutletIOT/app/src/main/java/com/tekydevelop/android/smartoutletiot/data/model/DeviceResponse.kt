package com.tekydevelop.android.smartoutletiot.data.model

import com.tekydevelop.android.smartoutletiot.data.mapper.DomainMappable
import com.tekydevelop.android.smartoutletiot.domain.model.DeviceData

class DeviceResponse(
    private val id: Int,
    private val uuid: Int,
    private val name: String,
    private val type: Int
) : DomainMappable<DeviceData>{
    override fun asDomain() = DeviceData (id, uuid,name,type)
}
