package com.tekydevelop.android.smartoutletiot.data.model

import com.google.gson.annotations.SerializedName

data class GatewayPinResponse(
    private val status: String,
    @SerializedName("rf_pin") private val rfPin: Int
)