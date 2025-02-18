package com.anupam.wishlist.presentation.components.wishlistcategory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WishListCategoryScreen(
    modifier: Modifier = Modifier,
    onWishListClick: (Int) -> Unit
) {
    Column(
        modifier = modifier.clickable {
            onWishListClick(1)
        }
    ) {
        Text(text = "WishListCategoryScreen")
        Spacer(modifier = Modifier.height(16.dp))
    }
}