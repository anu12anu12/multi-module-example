package com.anupam.reviews.presentation.reviewlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.anupam.reviews.domain.usecases.GetReviewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReviewListViewModel @Inject constructor(
    getReviewsUseCase: GetReviewsUseCase
): ViewModel() {
    val uiState = getReviewsUseCase().cachedIn(viewModelScope)
}