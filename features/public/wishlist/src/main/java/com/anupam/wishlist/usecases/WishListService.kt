package com.anupam.wishlist.usecases

interface WishListService {
    suspend fun removeWishList(wishListId: Int): Boolean
}