package com.example.bachelors.features.authentication.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bachelors.authsdk.AuthSDK
import com.bachelors.authsdk.callback.AuthSdkCallback
import com.bachelors.authsdk.data.response.AuthError
import com.bachelors.authsdk.data.response.AuthSuccessResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoggedIn: Boolean = false,
    val errorMessage: String? = null,
    val authorized: Boolean? = false
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

    fun handleEvent(event: LogInEvent){
        when(event){
            LogInEvent.logIn -> {
                _uiState.value = LoginUiState(username = "a", password = "123", isLoggedIn = true, authorized = true)
            }
            else -> return
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

sealed class LogInEvent(){
    data object logIn : LogInEvent()
}