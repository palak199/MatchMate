package com.example.matchmatee.data.local.dao

import androidx.room.*
import com.example.matchmatee.data.local.entity.UserProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfiles(profiles: List<UserProfileEntity>)

    @Query("SELECT * FROM user_profiles WHERE isAccepted is NULL")
    fun getUndecidedProfiles() : Flow<List<UserProfileEntity>>

    @Query("SELECT * FROM user_profiles WHERE isAccepted = 1")
    fun getAcceptedProfiles(): Flow<List<UserProfileEntity>>

    @Update
    suspend fun updateProfile(profile: UserProfileEntity)
}