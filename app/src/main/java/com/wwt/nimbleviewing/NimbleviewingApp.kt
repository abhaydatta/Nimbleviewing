package com.wwt.nimbleviewing

import android.app.Application
import com.wwt.nimbleviewing.domain.di.appModule
import com.wwt.nimbleviewing.domain.di.databaseModule
import com.wwt.nimbleviewing.domain.di.repoModule
import com.wwt.nimbleviewing.domain.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NimbleviewingApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NimbleviewingApp)
            modules(listOf(appModule, repoModule, viewModelModule, databaseModule))
        }
    }
}