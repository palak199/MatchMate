package com.example.matchmatee.data.remote.dto

data class UserDto(
    val name: NameDto,
    val dob: DobDto,
    val location: LocationDto,
    val picture: PictureDto
)
data class NameDto(val first: String, val last: String)
data class DobDto(val age: Int)
data class LocationDto(val city: String)
data class PictureDto(val large: String)
