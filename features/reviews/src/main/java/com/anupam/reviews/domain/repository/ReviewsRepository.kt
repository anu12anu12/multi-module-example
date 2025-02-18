package com.anupam.reviews.domain.repository

import androidx.paging.PagingData
import com.anupam.reviews.data.models.entitities.ReviewEntity
import kotlinx.coroutines.flow.Flow

interface ReviewsRepository {
    fun getReviews(): Flow<PagingData<ReviewEntity>>
}