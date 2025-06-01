package com.example.matchmate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.matchmate.data.local.entity.UserProfileEntity
import com.example.matchmate.data.local.dao.UserProfileDao

@Database(
    entities = [UserProfileEntity::class],
    version = 2,
    exportSchema = false
)
abstract class Database: RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
}