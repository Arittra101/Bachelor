package com.example.bachelors.features.common.manager

import com.example.bachelors.features.common.model.ToastData
import com.example.bachelors.features.common.model.ToastType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object ToastManager {
    private val _toastState = MutableSharedFlow<ToastData?>(replay = 1)
    val toastState = _toastState.asSharedFlow()

    suspend fun show(message: String, type: ToastType, duration: Long = 3000L) {
        _toastState.emit(ToastData(message, type, duration))
    }

    suspend fun dismiss() {
        _toastState.emit(null)
    }
}