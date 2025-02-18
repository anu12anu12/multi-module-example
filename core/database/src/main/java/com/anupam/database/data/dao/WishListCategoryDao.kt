package com.anupam.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anupam.database.data.entities.WishListCategoryEntity
import com.anupam.database.data.entities.WishListItemEntity

@Dao
interface WishListCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: WishListCategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<WishListCategoryEntity>)

    @Query("SELECT * FROM wishlistcategoryentity")
    suspend fun getAllCategories(): List<WishListCategoryEntity>

    @Query("DELETE FROM wishlistcategoryentity")
    suspend fun deleteAllCategories()

    @Query("DELETE FROM wishlistcategoryentity WHERE id = :categoryId")
    suspend fun deleteCategoryById(categoryId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishListItem(item: WishListItemEntity)

    @Query("""
    SELECT c.*, w.* 
    FROM wishlistcategoryentity c
    INNER JOIN wishlistentity w ON c.id = w.categoryId
    WHERE (w.createdAt > :timeStamp AND :isUpcoming = 1)
       OR (w.createdAt < :timeStamp AND :isUpcoming = 0)
    ORDER BY w.createdAt DESC
""")
    suspend fun getCategoriesWithItemsByTime(
        timeStamp: Long,
        isUpcoming: Int
    ): List<WishListCategoryEntity>
}