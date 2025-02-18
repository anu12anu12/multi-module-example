package com.anupam.wishlist.domain.usecases

import com.anupam.wishlist.domain.repository.WistListRepository
import javax.inject.Inject

class WishListRemoveUseCase @Inject constructor(
    private val wishListRepository: WistListRepository
) {
    suspend operator fun invoke(categoryId: Int, wishListId: Int) {
        wishListRepository.deleteWishListItemFromCategory(categoryId, wishListId)
    }
}