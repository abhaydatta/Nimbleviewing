package com.wwt.nimbleviewing.data.api

import com.wwt.nimbleviewing.data.model.Album
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    suspend fun getAlbums(isNetworkConnected: Boolean): Flow<List<Album>?>
}