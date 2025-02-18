package com.anupam.reviews.data.models.entitities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "reviewentity")
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "reviewId") val reviewId: Long?,
    @ColumnInfo(name = "authorName") val authorName: String?,
    @ColumnInfo(name = "authorCountry") val authorCountry: String?,
    @ColumnInfo(name = "authorPhoto") val authorPhoto: String?,
    @ColumnInfo(name = "message") val message: String?,
    @ColumnInfo(name = "rating") val rating: Float?,
    @ColumnInfo(name = "created") val created: String?,
)
