package com.wwt.nimbleviewing.data.api

import com.wwt.nimbleviewing.data.model.Album
import com.wwt.nimbleviewing.data.model.AlbumArt
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ApiHelper {
    suspend fun getAlbums(): Flow<List<Album>?>
}