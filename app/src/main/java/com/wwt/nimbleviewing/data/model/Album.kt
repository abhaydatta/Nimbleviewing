package com.wwt.nimbleviewing.data.model

import com.squareup.moshi.Json

data class Album(
    @Json(name = "UserId")
    val userId: Int = 0,
    @Json(name = "Id")
    val id: Int = 0,
    @Json(name = "Title")
    val title: String = ""
    )

data class AlbumArt(
    @Json(name = "Albums")
    val albumId: Int,
    @Json(name = "Id")
    val id: Int,
    @Json(name = "Title")
    val title: String,
    @Json(name = "Url")
    val url: String,
    @Json(name = "ThumbnailUrl")
    val thumbnailUrl: String)