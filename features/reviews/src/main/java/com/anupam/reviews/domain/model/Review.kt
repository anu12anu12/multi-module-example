package com.anupam.reviews.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Review(
    val id: Long?,
    val message: String,
    val rating: Float,
    val authorName: String,
    val authorCountry: String,
    val authorPhoto: String,
    val created: String
)