package com.tekydevelop.android.smartoutletiot.presentation.overview.bottom_sheet

import com.tekydevelop.android.smartoutletiot.domain.model.DeviceData

interface OnBottomSheetActions {
    /**
     * Select device action
     */
    fun selectDevice(device: DeviceData)

    /**
     * Notify device list update
     */
    fun updateList()

    /**
     * Edit gateway device
     */
    fun editDevice(device: DeviceData)
}