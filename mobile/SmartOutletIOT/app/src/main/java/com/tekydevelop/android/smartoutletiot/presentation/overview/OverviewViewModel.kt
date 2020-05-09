package com.tekydevelop.android.smartoutletiot.presentation.overview

import androidx.lifecycle.MutableLiveData
import com.tekydevelop.android.smartoutletiot.common.BaseViewModel
import com.tekydevelop.android.smartoutletiot.data.model.OutletResponse
import com.tekydevelop.android.smartoutletiot.domain.model.DeviceData
import com.tekydevelop.android.smartoutletiot.domain.usecase.DeviceCases
import com.tekydevelop.android.smartoutletiot.domain.usecase.OverviewCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class OverviewViewModel(
    private val overviewCase: OverviewCase,
    private val deviceCases: DeviceCases
) : BaseViewModel() {

    var availableDevices = MutableLiveData<List<DeviceData>>()
    var deviceControl = MutableLiveData<OutletResponse>()

    var error = MutableLiveData<Throwable>()

    fun loadGatewayDevices() {
        disposables += deviceCases.executeAvailableDevices()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                availableDevices.postValue(it.sortedBy { it.id })
            }, {
                error.postValue(it)
            })
    }

    fun setOutletState(device: DeviceData) {
        disposables += overviewCase.execute(OverviewCase.Params(device.isChecked, device))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                deviceControl.postValue(it)
            }, {
                error.postValue(it)
            })
    }
}