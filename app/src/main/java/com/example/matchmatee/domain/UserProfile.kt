package com.example.matchmatee.domain

import com.example.matchmatee.data.local.entity.UserProfileEntity

data class UserProfile (
    val id: String,
    val name: String,
    val age: Int,
    val city: String,
    val imageUrl: String,
    val education: String,
    val religion: String,
    val community: String,
    val isAccepted: Boolean? = null, // null = not yet decided
    val matchScore: Int = 0
)

fun UserProfileEntity.toDomain(): UserProfile {
    return UserProfile(
        id = id,
        name = name,
        age = age,
        city = city,
        imageUrl = imageUrl,
        education = education,
        religion = religion,
        community = community,
        isAccepted = isAccepted,
        matchScore = matchScore
    )
}

// UserProfile → UserProfileEntity
fun UserProfile.toEntity(): UserProfileEntity {
    return UserProfileEntity(
        id = id,
        name = name,
        age = age,
        city = city,
        imageUrl = imageUrl,
        education = education,
        religion = religion,
        community = community,
        isAccepted = isAccepted,
        matchScore = matchScore
    )
}