package com.example.matchmatee.data.repository

import android.content.Context
import android.util.Log
import com.example.matchmatee.data.local.DatabaseProvider
import com.example.matchmatee.domain.UserProfile
import com.example.matchmatee.domain.toDomain
import com.example.matchmatee.domain.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserProfileRepository(context: Context) {
    private val userDao = DatabaseProvider.getDatabase(context).userProfileDao()

    fun getAllProfiles(): Flow<List<UserProfile>> {
        return userDao.getAllProfiles().map {
            list -> list.map { it.toDomain() }
        }
    }

    fun getAcceptedProfiles(): Flow<List<UserProfile>> {
        return userDao.getAcceptedProfiles().map {
                list -> list.map { it.toDomain() }
        }
    }

    suspend fun insertProfiles(profiles: List<UserProfile>) {
        userDao.insertProfiles(profiles.map { it.toEntity() })
    }

    suspend fun updateProfile(copy: UserProfile) {
        userDao.updateProfile(copy.toEntity())
        Log.d("plk0", "updated")
    }
}