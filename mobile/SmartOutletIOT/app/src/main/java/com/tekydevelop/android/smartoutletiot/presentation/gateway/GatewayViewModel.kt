package com.tekydevelop.android.smartoutletiot.presentation.gateway

import androidx.lifecycle.MutableLiveData
import com.tekydevelop.android.smartoutletiot.common.BaseViewModel
import com.tekydevelop.android.smartoutletiot.domain.usecase.GatewayCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class GatewayViewModel(private val gatewayCase: GatewayCase) : BaseViewModel() {

    val success = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()

    fun checkGateway() {
        disposables += gatewayCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                success.postValue(true)
            }, {
                error.postValue(true)
            })
    }
}