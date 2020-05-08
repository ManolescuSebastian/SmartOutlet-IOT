package com.tekydevelop.android.smartoutletiot.presentation.gateway

import androidx.lifecycle.MutableLiveData
import com.tekydevelop.android.smartoutletiot.common.BaseViewModel
import com.tekydevelop.android.smartoutletiot.data.model.StatusResponse
import com.tekydevelop.android.smartoutletiot.domain.usecase.GatewayCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class GatewayViewModel(private val gatewayCase: GatewayCase) : BaseViewModel() {

    val gatewayData = MutableLiveData<StatusResponse>()
    val error = MutableLiveData<Throwable>()

    fun checkGateway() {
        disposables += gatewayCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                gatewayData.postValue(it)
            }, {
                error.postValue(it)
            })
    }
}