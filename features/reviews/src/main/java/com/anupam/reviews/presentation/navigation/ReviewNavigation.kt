package com.anupam.reviews.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.anupam.common.result.Result
import com.anupam.reviews.presentation.reviewlist.components.ReviewListRoute
import kotlinx.serialization.Serializable

@Serializable
object ReviewModule

@Serializable
object ReviewList
fun NavGraphBuilder.reviewListGraph(
    onCreatedWishList: (String) -> Unit,
    reviewListGraphBuilder: NavGraphBuilder.() -> Unit
) {
    navigation<ReviewModule>(
        startDestination = ReviewList
    ) {
        composable<ReviewList> {
            val savedWishList = it.savedStateHandle.getStateFlow("savedWishList", Result.Success(false)).collectAsStateWithLifecycle()
            ReviewListRoute(
                modifier = Modifier,
                wistListStatus = savedWishList.value.data,
                onCreateWishList = onCreatedWishList
            )
        }
        reviewListGraphBuilder()
    }
}