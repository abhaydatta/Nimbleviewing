package com.wwt.nimbleviewing.core.handler

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.wwt.nimbleviewing.core.NimbleViewModel
import com.wwt.nimbleviewing.core.models.NBError

interface NBErrorProvider : LifecycleOwner {

    fun observeErrorModel(nimbleViewModel: NimbleViewModel?) {
        nimbleViewModel?.errorLiveData?.observe(this, Observer {
            showError(it)
        })
    }

    fun showError(error: NBError)
}