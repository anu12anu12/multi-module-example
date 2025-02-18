package com.anupam.wishlist.presentation.components.createwishlist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun CreateWishListRoute(
    modifier: Modifier = Modifier,
    categoryName: String,
    onSaveWishList: (Boolean) -> Unit,
) {
    CreateWishListScreen(
        modifier = modifier,
        categoryName = categoryName,
        saveCategory = onSaveWishList
    )
}