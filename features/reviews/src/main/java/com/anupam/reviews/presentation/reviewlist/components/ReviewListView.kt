package com.anupam.reviews.presentation.reviewlist.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.anupam.reviews.domain.model.Review

@Composable
internal fun ReviewListView(
    modifier: Modifier = Modifier,
    reviews: LazyPagingItems<Review>,
    wistListStatus: Boolean = false,
    onCreateWishList: (String) -> Unit
) {
    Column {
        if (wistListStatus) {
            Text(
                text = "WishList Created",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(reviews.itemCount) { index ->
                reviews[index]?.let { review ->
                    ReviewItem(
                        review = review,
                        onItemClick = {
                            onCreateWishList(it)
                        }
                    )
                }
            }

            // Handle loading state at the end of the list
            when (reviews.loadState.append) {
                is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                }
                is LoadState.Error -> {
                    item {
                        Text(
                            text = "Error loading more reviews",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                else -> {}
            }
            // Handle initial loading or empty state
            if (reviews.loadState.refresh == LoadState.Loading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            } else if (reviews.itemCount == 0 && reviews.loadState.refresh is LoadState.NotLoading) {
                item {
                    Text(
                        text = "No reviews available",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
