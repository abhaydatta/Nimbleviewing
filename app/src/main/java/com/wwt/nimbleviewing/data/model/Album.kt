package com.wwt.nimbleviewing.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
    tableName = "album"
)
data class Album(
    @Json(name = "UserId")
    val userId: Int = 0,
    @Json(name = "Id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @Json(name = "Title")
    val title: String = ""
)

