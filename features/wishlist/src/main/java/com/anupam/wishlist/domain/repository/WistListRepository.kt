package com.anupam.wishlist.domain.repository

import com.anupam.wishlist.domain.models.WishListCategoryItem
import com.anupam.wishlist.domain.models.WishListItem

interface WistListRepository {
    suspend fun getAllCategories(timeStamp: Long, isUpComing: Int): List<WishListCategoryItem>
    suspend fun getAllWishLists(timeStamp: Long, categoryId: Int, isUpComing: Int): List<WishListItem>
    suspend fun deleteCategory(categoryId: Int)

    suspend fun deleteWishListItemFromCategory(categoryId: Int, wishListId: Int)
    suspend fun createCategory(categoryName: String)
    suspend fun addWishListItemToCategory(categoryId: Int, item: WishListItem)
}