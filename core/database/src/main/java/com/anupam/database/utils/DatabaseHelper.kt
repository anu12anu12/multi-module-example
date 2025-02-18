package com.anupam.database.utils

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object DatabaseHelper {
    fun <T : RoomDatabase> createRoomDatabase(
        context: Context,
        databaseClass: Class<T>,
        databaseName: String
    ): T {
        return Room.databaseBuilder(
            context,
            databaseClass,
            databaseName
        ).build()
    }
}