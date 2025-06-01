package com.example.matchmate.utils

fun CalculateMatchScore(profileAge: Int, userAge: Int, profileReligion: String, userReligion: String): Int {
    val ageDiff = kotlin.math.abs(profileAge-userAge)
    val ageScore =  when {
        ageDiff <= 5 -> 60
        ageDiff <= 7 -> 55
        ageDiff <= 10 -> 50
        ageDiff <= 20 -> 40
        else -> 10
    }

    val religionScore = when {
        profileReligion == userReligion -> 40
        else -> 10
    }

    return ageScore + religionScore
}