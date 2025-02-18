package com.anupam.reviews.data.models.entitities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "reviewremotekeys")
data class ReviewRemoteKeysEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "reviewId") val reviewId: Long,
    @ColumnInfo(name = "prevOffset") val prevOffset: Int?,
    @ColumnInfo(name = "nextOffset") val nextOffset: Int?
)