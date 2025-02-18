package com.anupam.wishlist.presentation.navigation

import android.util.Log
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.anupam.wishlist.presentation.components.createwishlist.CreateWishListRoute
import com.anupam.wishlist.presentation.components.wishlist.WishListRoute
import com.anupam.wishlist.presentation.components.wishlistcategory.WishListCategoryRoute
import kotlinx.serialization.Serializable

@Serializable
data object WishListModule

@Serializable
data object CategoryWishListRoute
@Serializable
data class WishListBasedOnCategoryId(val categoryId: Int)
@Serializable
data class WishListCreateRoute(val categoryName: String)
fun NavHostController.navigateToWishList(categoryId: Int) {
    navigate(WishListBasedOnCategoryId(categoryId))
}

fun NavHostController.navigateToCreateWishList(categoryName: String) {
    navigate(WishListCreateRoute(categoryName))
}

fun NavGraphBuilder.wishListGraph(
        onWishListClick: (Int) -> Unit,
        onSavedWishList: (Boolean) -> Unit,
        wishListDestination: NavGraphBuilder.() -> Unit
    ) {
    navigation<WishListModule>(
        startDestination = CategoryWishListRoute
    ) {
        composable<CategoryWishListRoute> {
            WishListCategoryRoute(
                modifier = Modifier,
                onWishListClick = onWishListClick
            )
        }
        dialog<WishListCreateRoute> { wishListCreateRoute ->
            val categoryName = wishListCreateRoute.toRoute<WishListCreateRoute>().categoryName
            CreateWishListRoute(
                modifier = Modifier,
                categoryName = categoryName,
                onSaveWishList = onSavedWishList
            )
        }

        composable<WishListBasedOnCategoryId> { wishListRoute ->
            val categoryId = wishListRoute.toRoute<WishListBasedOnCategoryId>().categoryId
            WishListRoute(
                modifier = Modifier,
                categoryId = categoryId
            )
        }

        wishListDestination()
    }
}