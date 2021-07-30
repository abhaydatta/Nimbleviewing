package com.wwt.nimbleviewing.domain.repository

import com.wwt.nimbleviewing.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getAlbums() =  apiHelper.getAlbums()
}