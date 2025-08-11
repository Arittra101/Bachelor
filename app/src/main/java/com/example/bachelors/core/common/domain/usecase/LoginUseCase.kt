package com.example.bachelors.core.common.domain.usecase

import com.example.bachelors.core.common.domain.model.User
import com.example.bachelors.core.common.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return authRepository.login(email, password)
    }
}