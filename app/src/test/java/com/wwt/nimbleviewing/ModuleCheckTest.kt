package com.wwt.nimbleviewing

import com.wwt.nimbleviewing.domain.di.appModule
import com.wwt.nimbleviewing.domain.di.repoModule
import org.junit.Test
import org.junit.experimental.categories.Category
import org.koin.test.AutoCloseKoinTest
import org.koin.test.category.CheckModuleTest


@Category(CheckModuleTest::class)
class ModuleCheckTest: AutoCloseKoinTest() {
    @Test
    fun checkModules() = org.koin.test.check.checkModules {

        modules(appModule)
    }
}