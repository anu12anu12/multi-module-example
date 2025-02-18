package com.anupam.reviews.data.repository

import androidx.paging.Pager
import com.anupam.reviews.data.models.entitities.ReviewEntity
import com.anupam.reviews.domain.repository.ReviewsRepository
import javax.inject.Inject

class ReviewsRepositoryImpl @Inject constructor(
    private val pager: Pager<Int, ReviewEntity>
): ReviewsRepository {
    override fun getReviews() = pager.flow
}