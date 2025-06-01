package com.example.matchmate.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profiles")
data class UserProfileEntity(
    @PrimaryKey val uuid: String,
    val name: String,
    val age: Int,
    val city: String,
    val imageUrl: String,
    val education: String,
    val profession: String,
    val religion: String,
    val community: String,
    val isAccepted: Boolean?, // null = undecided
    val matchScore: Int
)
