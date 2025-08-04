package com.example.bachelors.features.common.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bachelors.core.common.conditional
import kotlinx.coroutines.delay

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    title: String? = null,
    isTopLevelScreen: Boolean = false,
    isXMlComponent: Boolean = false,
    zeroBottomPadding: Boolean = false,
    showBackButton: Boolean = true,
    onBackPress: (() -> Unit)? = null,
    topBarContent: (@Composable () -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            when {
                topBarContent != null -> topBarContent()
                title != null -> HsTopBar(
                    title = title,
                    showBackButton = showBackButton,
                    onBackPress = onBackPress
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(innerPadding)
                .conditional(
                    isTopLevelScreen = isTopLevelScreen,
                    isXML = isXMlComponent,
                    isZeroBottomPadding = zeroBottomPadding,
                    ifTrue = {
                        Modifier.padding(
                            bottom = innerPadding.calculateBottomPadding() + 56.dp
                        )
                    },
                    ifXMLTrue = {
                        Modifier.padding(
                            bottom = 56.dp
                        )
                    },
                    ifZeroBottomBarTrue = {
                        Modifier.padding(
                            bottom = 0.dp
                        )
                    },
                )
                .then(modifier)
        ) {
            content(innerPadding)
        }
    }
}