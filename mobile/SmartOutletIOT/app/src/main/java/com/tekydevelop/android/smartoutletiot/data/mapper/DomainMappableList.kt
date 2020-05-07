package com.tekydevelop.android.smartoutletiot.data.mapper


interface DomainMappableList<R> {
    fun asDomain(): List<R>
}