package com.example.bachelors.features.authentication.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoggedIn: Boolean = false,
    val errorMessage: String? = null, // For login error (toast)
    val authorized: Boolean? = false,
    val emailError: String? = null,
    val passwordError: String? = null
)

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onUsernameChanged(value: String) {
        _uiState.update { it.copy(username = value) }
        validateEmail()
    }

    fun onPasswordChanged(value: String) {
        _uiState.update { it.copy(password = value) }
        validatePassword()
    }

    private fun validateEmail() {
        var emailError: String? = null
        if (_uiState.value.username.isBlank()) {
            emailError = "Email is required"
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(_uiState.value.username)
                .matches()
        ) {
            emailError = "Invalid email address"
        }
        _uiState.update { it.copy(emailError = emailError) }
    }

    private fun validatePassword() {
        var passwordError: String? = null
        if (_uiState.value.password.isBlank()) {
            passwordError = "Password is required"
        } else if (_uiState.value.password.length < 6) {
            passwordError = "Password must be at least 6 characters"
        }
        _uiState.update { it.copy(passwordError = passwordError) }
    }

    private fun validateFields(): Boolean {
        return _uiState.value.emailError == null &&
                _uiState.value.passwordError == null &&
                _uiState.value.password.isNotBlank() &&
                _uiState.value.username.isNotBlank()
    }

    private fun login() {
        if (!validateFields()) return
        var errorMessage: String? = null
        val currentState = _uiState.value
        if (currentState.username == "admin@gmail.com" && currentState.password == "123456") {
            _uiState.update { it.copy(isLoggedIn = true) }
        } else {
            errorMessage = "Invalid username or password"
        }
        _uiState.update { it.copy(errorMessage = errorMessage) }
    }
    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun handleEvent(event: LogInEvent) {
        when (event) {
            LogInEvent.logIn -> {
                login()
            }

            else -> return
        }
    }


}

sealed class LogInEvent() {
    data object logIn : LogInEvent()
}