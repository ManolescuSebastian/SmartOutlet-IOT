package com.tekydevelop.android.smartoutletiot.data.mapper

interface DomainMappable<R> {
    fun asDomain(): R
}