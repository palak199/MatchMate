package com.example.matchmate.data.remote

import com.example.matchmate.data.remote.dto.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/")
    suspend fun getUsers(@Query("results") count: Int = 10): UserResponse
}