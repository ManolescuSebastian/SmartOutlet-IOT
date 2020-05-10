package com.tekydevelop.android.smartoutletiot.domain.repository

import com.tekydevelop.android.smartoutletiot.data.model.OutletResponse
import io.reactivex.Single

interface OverviewRepository {
    /**
     * Change device values ON / OFF
     */
    fun outletStatus(
        status: Boolean,
        id: Int,
        uuid: Int,
        name: String,
        type: Int
    ): Single<OutletResponse>
}