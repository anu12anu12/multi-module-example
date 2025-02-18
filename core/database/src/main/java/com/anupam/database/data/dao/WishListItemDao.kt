package com.anupam.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.anupam.database.data.entities.WishListItemEntity

@Dao
interface WishListItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishListItem(item: WishListItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishListItems(items: List<WishListItemEntity>)

    @Query("SELECT * FROM wishlistentity WHERE categoryId = :categoryId")
    suspend fun getItemsByCategoryId(categoryId: Long): List<WishListItemEntity>

    @Query("DELETE FROM wishlistentity WHERE itemId = :itemId")
    suspend fun deleteItemById(itemId: Int)

    @Transaction
    suspend fun addWishListInCategory(categoryId: Int, item: WishListItemEntity) {
        val itemWithCategory = item.copy(categoryId = categoryId)

        insertWishListItem(itemWithCategory)
    }

    @Query("""
    SELECT * 
    FROM wishlistentity 
    WHERE categoryId = :categoryId
      AND ((createdAt > :currentTimestamp AND :isUpcoming = 1)
       OR (createdAt < :currentTimestamp AND :isUpcoming = 0))
    ORDER BY createdAt ASC
""")
    suspend fun getWishListItemsByTime(
        currentTimestamp: Long,
        isUpcoming: Int,
        categoryId: Int
    ): List<WishListItemEntity>
}
