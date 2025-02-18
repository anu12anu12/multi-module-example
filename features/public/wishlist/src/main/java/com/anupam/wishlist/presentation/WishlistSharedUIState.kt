package com.anupam.wishlist.presentation

data class WishlistSharedUIState (
    val isSuccessful: Boolean = true,
    val savedWishListId: Int = 0,
    val error: DataError? = null,
)