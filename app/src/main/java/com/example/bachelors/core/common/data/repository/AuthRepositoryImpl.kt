package com.example.bachelors.core.common.data.repository

import com.example.bachelors.core.common.data.remote.apiservice.AuthApiService
import com.example.bachelors.core.common.data.remote.dto.LoginRequestDto
import com.example.bachelors.core.common.domain.model.User
import com.example.bachelors.core.common.domain.repository.AuthRepository

class AuthRepositoryImpl(private val authApiService: AuthApiService) : AuthRepository {
    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            val response = authApiService.login(LoginRequestDto(email, password))
            val user = User(
                response.id,
                response.name,
                response.email,
                response.token,
            )
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }
}