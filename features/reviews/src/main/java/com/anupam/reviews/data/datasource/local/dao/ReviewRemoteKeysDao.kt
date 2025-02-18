package com.anupam.reviews.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.anupam.reviews.data.models.entitities.ReviewRemoteKeysEntity

@Dao
interface ReviewRemoteKeysDao {
    @Upsert
    suspend fun insertAll(remoteKey: List<ReviewRemoteKeysEntity>)

    @Query("SELECT * FROM reviewremotekeys WHERE reviewId = :reviewId")
    suspend fun getRemoteKeyById(reviewId: Long): ReviewRemoteKeysEntity?

    @Query("DELETE FROM reviewremotekeys")
    suspend fun clearAllRemoteKeys()

    @Query("SELECT * FROM reviewremotekeys ORDER BY id ASC")
    suspend fun getAllRemoteKeys(): List<ReviewRemoteKeysEntity>
}