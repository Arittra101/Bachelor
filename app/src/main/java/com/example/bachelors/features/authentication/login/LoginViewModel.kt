package com.example.bachelors.features.authentication.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoggedIn: Boolean = false,
    val errorMessage: String? = null,
    val authorized: Boolean? = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val isPasswordVisible: Boolean = false
)

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onUsernameChanged(value: String) {
        _uiState.update { state ->
            val emailError = when {
                value.isBlank() -> "Email is required"
                !android.util.Patterns.EMAIL_ADDRESS.matcher(value)
                    .matches() -> "Invalid email address"

                else -> null
            }
            state.copy(username = value, emailError = emailError)
        }
    }

    fun onPasswordChanged(value: String) {
        _uiState.update { state ->
            val passwordError = when {
                value.isBlank() -> "Password is required"
                value.length < 6 -> "Password must be at least 6 characters"
                else -> null
            }
            state.copy(password = value, passwordError = passwordError)
        }
    }

    private fun togglePasswordVisibility() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    private fun validateFields(): Boolean {
        val state = _uiState.value
        return state.emailError == null &&
                state.passwordError == null &&
                state.username.isNotBlank() &&
                state.password.isNotBlank()
    }

    private fun login() {
        if (!validateFields()) return
        val success = _uiState.value.username == "admin@gmail.com" &&
                _uiState.value.password == "123456"

        _uiState.update {
            it.copy(
                isLoggedIn = success,
                errorMessage = if (success) null else "Invalid username or password"
            )
        }
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun handleEvent(event: LogInEvent) {
        when (event) {
            LogInEvent.LogIn -> login()
            LogInEvent.TogglePasswordVisibility -> togglePasswordVisibility()
        }
    }
}

sealed class LogInEvent {
    data object LogIn : LogInEvent()
    data object TogglePasswordVisibility : LogInEvent()
}