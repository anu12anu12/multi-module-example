package com.anupam.wishlist.domain.models

data class WishListItem(
    val id: Int,
    val categoryId: Int,
    val image: String,
    val title: String,
    val description: String,
    val price: Double,
    val createdAt: Long
)
