package com.anupam.wishlist.data.repository

import com.anupam.database.data.dao.WishListCategoryDao
import com.anupam.database.data.dao.WishListItemDao
import com.anupam.database.data.entities.WishListCategoryEntity
import com.anupam.database.data.entities.WishListItemEntity
import com.anupam.wishlist.domain.models.WishListCategoryItem
import com.anupam.wishlist.domain.models.WishListItem
import com.anupam.wishlist.domain.repository.WistListRepository
import javax.inject.Inject

class WistListRepositoryImpl @Inject constructor(
    private val wishListCategoryDao: WishListCategoryDao,
    private val wishListItemDao: WishListItemDao,
): WistListRepository {
    override suspend fun getAllCategories(timeStamp: Long, isUpComing: Int): List<WishListCategoryItem> {
        return wishListCategoryDao.getCategoriesWithItemsByTime(timeStamp = timeStamp, isUpcoming = 1, ).map { WishListCategoryItem(
            id = it.id,
            categoryName = it.categoryName ?: "",
            image = ""
        )}
    }


    override suspend fun getAllWishLists(timeStamp: Long, categoryId: Int, isUpComing: Int): List<WishListItem> {
        return wishListItemDao.getWishListItemsByTime(timeStamp, isUpComing, categoryId).map { WishListItem(
            id = it.id,
            categoryId = it.categoryId,
            image = it.itemImageUrl ?: "",
            title = it.itemName ?: "",
            description = it.itemDescription ?: "",
            price = it.itemPrice ?: 0.0,
            createdAt = it.createdAt
        )}
    }

    override suspend fun deleteCategory(categoryId: Int) {
        wishListCategoryDao.deleteCategoryById(categoryId)
    }

    override suspend fun deleteWishListItemFromCategory(categoryId: Int, wishListId: Int) {
        wishListItemDao.deleteItemById(categoryId)
    }

    override suspend fun createCategory(categoryName: String) {
        wishListCategoryDao.insertCategory(
            WishListCategoryEntity(
                categoryName = categoryName
            )
        )
    }

    override suspend fun addWishListItemToCategory(categoryId: Int, item: WishListItem) {
        wishListItemDao.addWishListInCategory(
            categoryId = categoryId,
            item = WishListItemEntity(
                id = item.id,
                itemId = item.id.toLong(),
                categoryId = categoryId,
                itemName = item.title,
                itemPrice = item.price,
                itemImageUrl = item.image,
                itemDescription = item.description,
                createdAt = item.createdAt
            )
        )
    }

}