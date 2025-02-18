package com.anupam.database.data.entities
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "wishlistentity",
    foreignKeys = [
        ForeignKey(
            entity = WishListCategoryEntity::class,
            parentColumns = ["id"], // The primary key in WishListCategoryEntity
            childColumns = ["categoryId"], // The foreign key in WishListItem
            onDelete = ForeignKey.CASCADE // Deletes items when the associated category is deleted
        )
    ]
)
data class WishListItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int, // Primary key
    @ColumnInfo(name = "itemId") val itemId: Long?, // Item ID
    @ColumnInfo(name = "categoryId") val categoryId: Int, // Foreign key referencing id in WishListCategoryEntity
    @ColumnInfo(name = "itemName") val itemName: String?, // Item name
    @ColumnInfo(name = "itemPrice") val itemPrice: Double?, // Item price
    @ColumnInfo(name = "itemImageUrl") val itemImageUrl: String?, // Image URL
    @ColumnInfo(name = "itemDescription") val itemDescription: String?, // Description,
    @ColumnInfo(name = "createdAt") val createdAt: Long // Timestamp when the item is added
)
