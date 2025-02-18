package com.anupam.reviews.presentation.reviewlist

import androidx.paging.PagingData
import com.anupam.reviews.domain.model.Review
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ReviewListUIState(
    val reviews: Flow<PagingData<Review>> = emptyFlow()
)