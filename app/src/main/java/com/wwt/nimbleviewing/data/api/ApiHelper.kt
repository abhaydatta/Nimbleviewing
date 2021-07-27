package com.wwt.nimbleviewing.data.api

import com.wwt.nimbleviewing.data.model.Album
import com.wwt.nimbleviewing.data.model.AlbumArt
import retrofit2.Response

interface ApiHelper {
    suspend fun getAlbums(): Response<List<Album>>
    suspend fun getAlbumPhoto(): Response<List<AlbumArt>>

}