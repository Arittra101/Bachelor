package com.example.bachelors.features.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bachelors.features.common.manager.ToastManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ToastHost() {
    val toastData by ToastManager.toastState.collectAsState(initial = null)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(toastData) {
        toastData?.let { data ->
            delay(data.duration)
            ToastManager.dismiss()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        toastData?.let { data ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppToast(
                    message = data.message,
                    type = data.type,
                    onDismiss = {
                        coroutineScope.launch { ToastManager.dismiss() }
                    }
                )
            }
        }
    }
}