package com.wwt.nimbleviewing.domain.repository

import com.wwt.nimbleviewing.data.api.ApiHelper

 open class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getAlbums(isNetworkConnected: Boolean) =  apiHelper.getAlbums(isNetworkConnected)
}