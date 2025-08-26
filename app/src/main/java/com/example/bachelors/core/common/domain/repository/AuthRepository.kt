package com.example.bachelors.core.common.domain.repository

import com.example.bachelors.core.common.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun logout()
}