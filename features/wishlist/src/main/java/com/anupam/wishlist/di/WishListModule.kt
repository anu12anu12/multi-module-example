package com.anupam.wishlist.di

import com.anupam.database.data.dao.WishListCategoryDao
import com.anupam.database.data.dao.WishListItemDao
import com.anupam.wishlist.data.repository.WistListRepositoryImpl
import com.anupam.wishlist.domain.repository.WistListRepository
import com.anupam.wishlist.domain.usecases.WishListRemoveUseCase
import com.anupam.wishlist.domain.usecases.WishListServiceImpl
import com.anupam.wishlist.usecases.WishListService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object WishListModule {

    @Provides
    fun provideWishListService(wishListRemoveUseCase: WishListRemoveUseCase): WishListService {
        return WishListServiceImpl(wishListRemoveUseCase)
    }
    @Provides
    fun provideWishListRepository(
        wishListCategoryDao: WishListCategoryDao,
        wishListItemDao: WishListItemDao
    ): WistListRepository {
        return WistListRepositoryImpl(
            wishListCategoryDao = wishListCategoryDao,
            wishListItemDao = wishListItemDao
        )
    }
}