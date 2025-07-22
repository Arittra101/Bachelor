package com.example.bachelors.features.common.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bachelors.core.common.conditional

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    title: String? = null,
    isTopLevelScreen: Boolean = false,
    isXMlComponent: Boolean = true,
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
                .background(Color.Black)
                .padding(innerPadding)
                .conditional(
                    isTopLevelScreen = isTopLevelScreen,
                    isXML = isXMlComponent,
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
                )
                .then(modifier)
        ) {
            content(innerPadding)
        }
    }
}