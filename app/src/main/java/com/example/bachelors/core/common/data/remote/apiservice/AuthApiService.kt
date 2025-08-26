package com.example.bachelors.core.common.data.remote.apiservice

import com.example.bachelors.core.common.data.remote.dto.LoginRequestDto
import com.example.bachelors.core.common.data.remote.dto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("login")
    suspend fun login(@Body request: LoginRequestDto): LoginResponseDto
}