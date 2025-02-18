package com.anupam.wishlist.presentation.components.createwishlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateWishListScreen(
    modifier: Modifier = Modifier,
    categoryName: String,
    saveCategory: (Boolean) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        TextField(
            value = categoryName,
            onValueChange = {},
            label = { Text("Create Category") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { saveCategory(true) },
            enabled = true,
            modifier = Modifier.
            height(52.dp),
        ) {
            Text("Save Category")
        }
    }
}