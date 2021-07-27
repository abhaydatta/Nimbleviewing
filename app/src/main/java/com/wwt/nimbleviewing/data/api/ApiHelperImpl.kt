package com.wwt.nimbleviewing.data.api

import com.wwt.nimbleviewing.data.model.Album
import com.wwt.nimbleviewing.data.model.AlbumArt
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) :ApiHelper {
    override suspend fun getAlbums(): Response<List<Album>>  = apiService.getAlbum()
    override suspend fun getAlbumPhoto(): Response<List<AlbumArt>> = apiService.getAlbumPhoto()
}