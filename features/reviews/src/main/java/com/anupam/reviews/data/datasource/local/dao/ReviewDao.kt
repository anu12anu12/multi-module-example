package com.anupam.reviews.data.datasource.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.anupam.reviews.data.models.entitities.ReviewEntity

@Dao
interface ReviewDao {
    @Upsert
    suspend fun insertOrReplaceReview(review: ReviewEntity)

    @Upsert
    suspend fun insertAll(reviews: List<ReviewEntity>)

    @Query("DELETE FROM reviewentity WHERE reviewId = :reviewId")
    suspend fun deleteReviewById(reviewId: Long)

    @Query("DELETE FROM reviewentity")
    suspend fun deleteAllReviews()

    @Query("SELECT * FROM reviewentity WHERE reviewId = :reviewId")
    suspend fun getReviewById(reviewId: Long): ReviewEntity

    @Query("SELECT * FROM reviewentity ORDER BY id ASC")
    fun getPagerSource(): PagingSource<Int, ReviewEntity>
}