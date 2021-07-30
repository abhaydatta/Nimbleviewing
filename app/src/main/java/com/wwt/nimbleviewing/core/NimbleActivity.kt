package com.wwt.nimbleviewing.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.wwt.nimbleviewing.core.handler.NBErrorProvider
import com.wwt.nimbleviewing.core.models.NBError
import com.wwt.nimbleviewing.data.util.NetworkHelper
import org.koin.android.ext.android.inject

abstract class NimbleActivity<B : ViewBinding, V : NimbleViewModel> : AppCompatActivity(),
    NBErrorProvider {
    protected lateinit var viewModel: V
    protected lateinit var binding: B

    protected val networkHelper: NetworkHelper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initBinding()
        setContentView(binding.root)
        viewModel = initViewModel()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        viewModel.onCreate()
        observeErrorModel(viewModel)
    }


    override fun showError(error: NBError) {
        // show alert box here
    }

    abstract fun initViewModel(): V
    abstract fun initBinding(): B
}