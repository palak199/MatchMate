package com.example.matchmate.data.remote.dto

data class UserDto(
    val name: NameDto,
    val dob: DobDto,
    val location: LocationDto,
    val picture: PictureDto,
    val login: LoginDto,
)

data class NameDto(val first: String, val last: String)
data class DobDto(val age: Int)
data class LocationDto(val city: String)
data class LoginDto(val uuid: String)
data class PictureDto(val medium: String)
