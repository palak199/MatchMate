package com.example.matchmatee.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profiles")
data class UserProfileEntity(
    @PrimaryKey val id: String,
    val name: String,
    val age: Int,
    val city: String,
    val imageUrl: String,
    val education: String,
    val religion: String,
    val community: String,
    val isAccepted: Boolean?, // null = undecided
    val matchScore: Int
)
