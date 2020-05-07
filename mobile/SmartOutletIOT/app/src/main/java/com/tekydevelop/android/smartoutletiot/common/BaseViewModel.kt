package com.tekydevelop.android.smartoutletiot.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}