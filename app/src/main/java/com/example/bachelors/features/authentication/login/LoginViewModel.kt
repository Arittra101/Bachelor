package com.example.bachelors.features.authentication.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoggedIn: Boolean = false,
    val errorMessage: String? = null
)

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onUsernameChanged(value: String) {
        _uiState.update { it.copy(username = value) }
    }

    fun onPasswordChanged(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun login() {
        val currentState = _uiState.value
        if (currentState.username == "admin" && currentState.password == "admin") {
            _uiState.update { it.copy(isLoggedIn = true, errorMessage = null) }
        } else {
            _uiState.update { it.copy(errorMessage = "Invalid credentials") }
        }
    }
}