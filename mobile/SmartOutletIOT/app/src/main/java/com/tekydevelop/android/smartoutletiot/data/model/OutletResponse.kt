package com.tekydevelop.android.smartoutletiot.data.model

import com.google.gson.annotations.SerializedName

data class OutletResponse(
    private val status: Boolean,
    private val state: String,
    @SerializedName("code_sent") private val codeSent: Int
)