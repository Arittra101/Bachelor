package com.example.bachelors.features.authentication.login

import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bachelors.authsdk.AuthSDK
import com.bachelors.authsdk.callback.AuthSdkCallback
import com.bachelors.authsdk.data.response.AuthError
import com.bachelors.authsdk.data.response.AuthSuccessResult
import androidx.lifecycle.viewModelScope
import com.bachelors.authsdk.AuthSDK
import com.bachelors.authsdk.callback.AuthSdkCallback
import com.bachelors.authsdk.data.response.AuthError
import com.bachelors.authsdk.data.response.AuthSuccessResult
import androidx.lifecycle.viewModelScope
import com.example.bachelors.core.common.domain.usecase.LoginUseCase
import com.example.bachelors.features.common.manager.ToastManager
import com.example.bachelors.features.common.model.ToastType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val authorized: Boolean? = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val isPasswordVisible: Boolean = false
)

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    private fun onUsernameChanged(value: String) {
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

    private fun onPasswordChanged(value: String) {
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
        return state.emailError == null && state.passwordError == null && state.username.isNotBlank() && state.password.isNotBlank()
    }

    private fun login() {
        if (!validateFields()) return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = loginUseCase(_uiState.value.username, _uiState.value.password)
            result.fold(onSuccess = {
                _uiState.update { it.copy(isLoggedIn = true, isLoading = false) }
            }, onFailure = { error ->
                _uiState.update {
                    it.copy(errorMessage = error.message, isLoading = false)
                }
                showErrorToast(error.message.toString())
            })
        }
    }

    fun handleEvent(event: LogInEvent) {
        when (event) {
            is LogInEvent.LogIn -> login()
            is LogInEvent.UsernameChanged -> onUsernameChanged(event.value)
            is LogInEvent.PasswordChanged -> onPasswordChanged(event.value)
            LogInEvent.TogglePasswordVisibility -> togglePasswordVisibility()
        }
    }

    private fun showErrorToast(message: String) {
        viewModelScope.launch {
            ToastManager.show(message, ToastType.ERROR)
        }
    }

    fun signIn(authSdk: AuthSDK) {
        viewModelScope.launch {
            authSdk.signIn(
                email = "arvindddd@gmail.com",
                password = "123456",
                username = "arvind1d013",
                object : AuthSdkCallback {
                    override fun onSuccess(result: AuthSuccessResult) {
                        Log.wtf("crey", result.data.toString())
                    }

                    override fun onError(error: AuthError) {
                        TODO("Not yet implemented")
                    }
                })


        }


    }

}

sealed class LogInEvent {
    data object LogIn : LogInEvent()
    data class UsernameChanged(val value: String) : LogInEvent()
    data class PasswordChanged(val value: String) : LogInEvent()
    data object TogglePasswordVisibility : LogInEvent()
}