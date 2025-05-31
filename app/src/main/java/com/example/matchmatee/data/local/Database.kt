package com.example.matchmatee.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.matchmatee.data.local.entity.UserProfileEntity
import com.example.matchmatee.data.local.dao.UserProfileDao

@Database(
    entities = [UserProfileEntity::class],
    version = 1,
    exportSchema = false
)
abstract class Database: RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
}