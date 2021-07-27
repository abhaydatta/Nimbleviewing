package com.wwt.nimbleviewing.domain.di

import com.wwt.nimbleviewing.domain.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(get())
    }
}