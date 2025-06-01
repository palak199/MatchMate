package com.example.matchmatee.domain

import com.example.matchmatee.data.local.entity.UserProfileEntity
import com.example.matchmatee.data.remote.dto.UserDto
import com.example.matchmatee.utils.CalculateMatchScore
import com.example.matchmatee.utils.ReligionCasteMap
fun UserProfileEntity.toDomain(): UserProfile {
    return UserProfile(
        uuid = uuid,
        name = name,
        age = age,
        city = city,
        imageUrl = imageUrl,
        education = education,
        religion = religion,
        community = community,
        isAccepted = isAccepted,
        profession = profession,
        matchScore = matchScore
    )
}

// UserProfile → UserProfileEntity
fun UserProfile.toEntity(): UserProfileEntity {
    return UserProfileEntity(
        uuid = uuid,
        name = name,
        age = age,
        city = city,
        profession = profession,
        imageUrl = imageUrl,
        education = education,
        religion = religion,
        community = community,
        isAccepted = isAccepted,
        matchScore = matchScore
    )
}

fun UserDto.toEntity(uuid: String): UserProfileEntity {
    val religion = ReligionCasteMap.keys.random()
    val caste = ReligionCasteMap[religion]?.random()

    val education = listOf("B.Com", "M.B.A", "B.Tech", "M.Sc", "M.Tech").random()
    val profession = listOf("Engineer", "Doctor", "Professor").random()
    val score = CalculateMatchScore(dob.age, 32, religion, "Hindu")
    return UserProfileEntity(
        name = "${name.first} ${name.last}",
        age = dob.age,
        uuid = login.uuid,
        city = location.city,
        profession = profession,
        imageUrl = picture.medium,
        education = education,     // TODO: take random values
        religion = religion,
        community = caste?:"N.A.",
        isAccepted = null,
        matchScore = score
    )
}