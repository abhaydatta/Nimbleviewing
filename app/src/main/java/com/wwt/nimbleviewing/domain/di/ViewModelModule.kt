package com.wwt.nimbleviewing.domain.di

import com.wwt.nimbleviewing.presentation.ui.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}