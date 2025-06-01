package com.example.matchmate.data.local.dao

import androidx.room.*
import com.example.matchmate.data.local.entity.UserProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfiles(profiles: List<UserProfileEntity>)

    @Query("SELECT * FROM user_profiles WHERE isAccepted is NULL")
    fun getUndecidedProfiles() : Flow<List<UserProfileEntity>>

    @Query("SELECT * FROM user_profiles WHERE isAccepted is not NULL")
    suspend fun getDecidedProfiles() : List<UserProfileEntity>

    @Query("SELECT * FROM user_profiles WHERE isAccepted = 1")
    fun getAcceptedProfiles(): Flow<List<UserProfileEntity>>

    @Update
    suspend fun updateProfile(profile: UserProfileEntity)

    @Query("""
    DELETE FROM user_profiles
    WHERE isAccepted IS NULL AND uuid NOT IN (
        SELECT uuid FROM user_profiles
        WHERE isAccepted IS NULL
        ORDER BY rowid DESC
        LIMIT :limit
    )
""")
        suspend fun deleteOldUndecidedProfiles(limit: Int)
    }