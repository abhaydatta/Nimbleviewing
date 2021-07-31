package com.wwt.nimbleviewing.data.api

import com.wwt.nimbleviewing.data.model.Album
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("albums")
    suspend fun getAlbum(): List<Album>?


}