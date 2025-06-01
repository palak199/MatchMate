package com.example.matchmatee.domain

data class UserProfile (
    val uuid: String,
    val name: String,
    val age: Int,
    val city: String,
    val imageUrl: String,
    val education: String,
    val religion: String,
    val community: String,
    val profession: String,
    val isAccepted: Boolean? = null, // null = not yet decided
    val matchScore: Int = 0
)
