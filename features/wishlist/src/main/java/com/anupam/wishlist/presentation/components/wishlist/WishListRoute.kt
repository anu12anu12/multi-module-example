package com.anupam.wishlist.presentation.components.wishlist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.anupam.wishlist.presentation.components.wishlist.WishListScreen


@Composable
internal fun WishListRoute(
    modifier: Modifier = Modifier,
    categoryId: Int) {
    WishListScreen(
        modifier = modifier,
        categoryId = categoryId
    )
}