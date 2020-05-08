package com.tekydevelop.android.smartoutletiot.presentation.adddevice

import androidx.lifecycle.MutableLiveData
import com.tekydevelop.android.smartoutletiot.common.BaseViewModel
import com.tekydevelop.android.smartoutletiot.data.model.StatusResponse
import com.tekydevelop.android.smartoutletiot.domain.model.DeviceData
import com.tekydevelop.android.smartoutletiot.domain.usecase.DeviceCases
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class DeviceViewModel(private val deviceCases: DeviceCases) : BaseViewModel() {

    var deviceData = MutableLiveData<StatusResponse>()
    var error = MutableLiveData<Throwable>()

    fun saveDevice(device: DeviceData) {
        disposables += deviceCases.executeAddCase(device)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                deviceData.postValue(it)
            }, {
                error.postValue(it)
            })
    }

    fun editDevice(device: DeviceData) {
        disposables += deviceCases.executeEditCase(device)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                deviceData.postValue(it)
            }, {
                error.postValue(it)
            })
    }

    fun deleteDevice(id: Int) {
        disposables += deviceCases.executeDeleteCase(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                deviceData.postValue(it)
            }, {
                error.postValue(it)
            })
    }
}
