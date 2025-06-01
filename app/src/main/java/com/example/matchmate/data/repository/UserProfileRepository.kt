package com.example.matchmate.data.repository

import android.content.Context
import android.util.Log
import com.example.matchmate.data.local.dao.UserProfileDao
import com.example.matchmate.data.remote.ApiService
import com.example.matchmate.domain.UserProfile
import com.example.matchmate.domain.toDomain
import com.example.matchmate.domain.toEntity
import com.example.matchmate.utils.retry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/*  here we get data from api and sync our local */
class UserProfileRepository(context: Context,
    private val dao: UserProfileDao,
    private val api: ApiService) {


    suspend fun syncProfiles(): Boolean {
        return try {
            val response = retry { api.getUsers() }

            val existingProfiles = dao.getDecidedProfiles().associateBy { it.uuid }

            val newProfiles = response.results.map { dto ->
                val uuid = dto.login.uuid
                val newProfile = dto.toEntity(uuid)

                // merge with old profile if exists
                val existing = existingProfiles[uuid]
                if (existing != null) {
                    newProfile.copy(isAccepted = existing.isAccepted)
                } else {
                    newProfile
                }
            }

            // merge with previously accepted/rejected entries not in new API result
            val leftoverProfiles = existingProfiles.values.filter {
                it.isAccepted != null && newProfiles.none { np -> np.uuid == it.uuid }
            }

            val finalProfiles = newProfiles + leftoverProfiles

            dao.insertProfiles(finalProfiles)
            dao.deleteOldUndecidedProfiles(limit = 50)
            true
        } catch(e: Exception) {
            Log.e("plk", "Failed to sync profiles")
            false
        }

    }

    fun getUndecidedProfiles(): Flow<List<UserProfile>> {
        return dao.getUndecidedProfiles().map {
            list -> list.map { it.toDomain() }
        }
    }

    fun getAcceptedProfiles(): Flow<List<UserProfile>> {
        return dao.getAcceptedProfiles().map {
                list -> list.map { it.toDomain() }
        }
    }

    suspend fun updateProfile(copy: UserProfile) {
        dao.updateProfile(copy.toEntity())
    }
}