package com.anupam.reviews.domain.usecases

import androidx.paging.map
import com.anupam.reviews.data.mapper.toReview
import com.anupam.reviews.domain.repository.ReviewsRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetReviewsUseCase @Inject constructor(
    private val reviewRepository: ReviewsRepository
) {
    operator fun invoke() = reviewRepository.getReviews().map { reviews -> reviews.map { it.toReview() } }
}