package com.example.matchmatee.data.remote

import com.example.matchmatee.data.remote.dto.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/")
    suspend fun getUsers(@Query("results") count: Int = 10): UserResponse
}