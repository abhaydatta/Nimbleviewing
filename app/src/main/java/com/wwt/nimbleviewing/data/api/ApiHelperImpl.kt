package com.wwt.nimbleviewing.data.api

import com.wwt.nimbleviewing.data.model.AlbumArt
import kotlinx.coroutines.flow.flow
import retrofit2.Response


class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getAlbums() = flow {
        val list = apiService.getAlbum()?.sortedBy { it.title }
        emit(list)
    }
}