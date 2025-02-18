package com.anupam.wishlist.domain.usecases

import com.anupam.wishlist.usecases.WishListService
import javax.inject.Inject

class WishListServiceImpl @Inject constructor(
    private val wishListRemoveUseCase: WishListRemoveUseCase
): WishListService {
    override suspend fun removeWishList(wishListId: Int): Boolean {
        wishListRemoveUseCase(0, wishListId)
        return true
    }
}