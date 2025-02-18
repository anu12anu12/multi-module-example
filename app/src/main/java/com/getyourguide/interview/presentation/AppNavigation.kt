package com.getyourguide.interview.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.anupam.common.result.Result
import com.anupam.reviews.presentation.navigation.ReviewModule
import com.anupam.reviews.presentation.navigation.reviewListGraph
import com.anupam.wishlist.presentation.navigation.navigateToCreateWishList
import com.anupam.wishlist.presentation.navigation.wishListGraph

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = ReviewModule,
    ) {
        reviewListGraph(
            onCreatedWishList = navController::navigateToCreateWishList,
        ) {
            // In case want to add more destination
        }
        wishListGraph(
            onWishListClick = {  },
            onSavedWishList = {
                navController.previousBackStackEntry?.savedStateHandle?.set("savedWishList", Result.Success(true))
                navController.popBackStack()
            },
            wishListDestination = {
                // In case want to add more destination
            }
        )
    }
}