package com.anupam.wishlist.presentation.components.wishlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WishListScreen(
    modifier: Modifier = Modifier,
    categoryId: Int
    ) {
    Column(
        modifier = modifier
    ) {
        Text(text = "WishListScreen : $categoryId")
        Spacer(modifier = Modifier.height(16.dp))
    }
}