package com.wwt.nimbleviewing.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wwt.nimbleviewing.core.models.NBError

abstract class NimbleViewModel : ViewModel() {
    val errorLiveData = MutableLiveData<NBError>()

    protected fun handleError(error: NBError) {
        errorLiveData.postValue(error)
    }

    open fun onCreate() {
        // override when onCreate required
    }
}
