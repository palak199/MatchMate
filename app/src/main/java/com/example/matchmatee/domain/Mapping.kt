package com.example.matchmatee.domain

import com.example.matchmatee.data.local.entity.UserProfileEntity
import com.example.matchmatee.data.remote.dto.UserDto

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

fun UserDto.toEntity(index: Int): UserProfileEntity {
    return UserProfileEntity(
        id = index.toString(), // or use UUID.randomUUID().toString()
        name = "${name.first} ${name.last}",
        age = dob.age,
        city = location.city,
        imageUrl = picture.large,
        education = "B.Tech",     // TODO: take random values
        religion = "Hindu",
        community = "Baniya",
        isAccepted = null,
        matchScore = (70..99).random() // TODO: change logic to use algo to find match score
    )
}