package com.tekydevelop.android.smartoutletiot.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
class DeviceData(
    val id: Int,
    val uuid: Int,
    val name: String,
    val type: Int
) : Parcelable {
    @IgnoredOnParcel
    @Volatile
    var isChecked: Boolean = false
}