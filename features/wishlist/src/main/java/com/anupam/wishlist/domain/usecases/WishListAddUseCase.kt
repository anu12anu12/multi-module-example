package com.anupam.wishlist.domain.usecases

import com.anupam.wishlist.domain.models.WishListItem
import com.anupam.wishlist.domain.repository.WistListRepository
import javax.inject.Inject

class WishListAddUseCase @Inject constructor(
    private val wishListRepository: WistListRepository
) {
    suspend operator fun invoke(categoryId: Int, item: WishListItem) {
        wishListRepository.addWishListItemToCategory(categoryId, item)
    }
}