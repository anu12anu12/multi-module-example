package com.anupam.reviews.di

import android.content.Context
import com.anupam.database.utils.DatabaseHelper
import com.anupam.reviews.data.datasource.local.AppDataBase
import com.anupam.reviews.data.datasource.local.dao.ReviewDao
import com.anupam.reviews.data.datasource.local.dao.ReviewRemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDataBase {
        return DatabaseHelper.createRoomDatabase(
            context = context,
            databaseClass = AppDataBase::class.java,
            databaseName = "app_database"
        )
    }

    @Singleton
    @Provides
    fun provideReviewDao(database: AppDataBase): ReviewDao {
        return database.reviewDao
    }

    @Singleton
    @Provides
    fun provideReviewRemoteKeysDao(database: AppDataBase): ReviewRemoteKeysDao {
        return database.reviewRemoteKeysDao
    }
}