package com.wwt.nimbleviewing.data.api

import android.util.Log
import com.wwt.nimbleviewing.data.db.AlbumDao
import kotlinx.coroutines.flow.flow


class ApiHelperImpl(private val apiService: ApiService, private val albumDao: AlbumDao) : ApiHelper {
    override suspend fun getAlbums(isNetworkConnected: Boolean) = flow {
        if (isNetworkConnected) {
            val list = apiService.getAlbum()?.sortedBy { it.title }
            albumDao.insertAll(list)
            emit(list)
        }
        else {
            val albumOfflineList = albumDao.getAllAlbum()
            emit(albumOfflineList)
        }

    }
}