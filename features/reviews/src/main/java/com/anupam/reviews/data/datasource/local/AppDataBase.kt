package com.anupam.reviews.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anupam.reviews.data.datasource.local.dao.ReviewDao
import com.anupam.reviews.data.datasource.local.dao.ReviewRemoteKeysDao
import com.anupam.reviews.data.models.entitities.ReviewEntity
import com.anupam.reviews.data.models.entitities.ReviewRemoteKeysEntity

@Database (
    entities = [ReviewEntity::class, ReviewRemoteKeysEntity::class],
    version = 1
)
abstract class AppDataBase: RoomDatabase() {
    abstract val reviewDao: ReviewDao
    abstract val reviewRemoteKeysDao: ReviewRemoteKeysDao
}