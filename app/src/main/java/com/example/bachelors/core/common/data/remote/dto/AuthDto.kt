package com.example.bachelors.core.common.data.remote.dto

data class LoginRequestDto(val email: String, val password: String)

data class LoginResponseDto(
    val id: String,
    val name: String,
    val email: String,
    val token: String
)