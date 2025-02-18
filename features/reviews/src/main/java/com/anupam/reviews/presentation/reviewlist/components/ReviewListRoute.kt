package com.anupam.reviews.presentation.reviewlist.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.anupam.reviews.presentation.reviewlist.ReviewListViewModel

@Composable
fun ReviewListRoute(
    modifier: Modifier = Modifier,
    wistListStatus: Boolean = false,
    viewModel: ReviewListViewModel = hiltViewModel(),
    onCreateWishList: (String) -> Unit
) {
    val uiState = viewModel.uiState.collectAsLazyPagingItems()
    ReviewListView(
        modifier = modifier,
        reviews = uiState,
        wistListStatus = wistListStatus,
        onCreateWishList = onCreateWishList
    )
}