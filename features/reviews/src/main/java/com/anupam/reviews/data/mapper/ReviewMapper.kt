package com.anupam.reviews.data.mapper

import com.anupam.reviews.data.models.entitities.ReviewEntity
import com.anupam.reviews.data.models.dto.ReviewResponse
import com.anupam.reviews.domain.model.Review

fun ReviewResponse.ReviewsDto.toReviewEntity(): ReviewEntity {
    return ReviewEntity(
        reviewId = id,
        authorName = author.fullName,
        authorCountry = author.country,
        authorPhoto = author.photo,
        message = message,
        rating = rating,
        created = created
    )
}

fun ReviewEntity.toReview(): Review {
    return Review(
        id = reviewId,
        authorName = authorName ?: "",
        authorCountry = authorCountry ?: "",
        authorPhoto = authorPhoto ?: "",
        message = message ?: "",
        rating = rating ?: 0f,
        created = created ?: ""
    )
}