package com.anupam.wishlist.presentation.components.wishlistcategory

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
internal fun WishListCategoryRoute(
    modifier: Modifier = Modifier,
    onWishListClick: (Int) -> Unit,
) {
    WishListCategoryScreen(
        modifier = modifier,
        onWishListClick = onWishListClick
    )
}