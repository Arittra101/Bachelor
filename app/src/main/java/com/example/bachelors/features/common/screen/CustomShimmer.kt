package com.example.bachelors.features.common.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Composable
fun CustomShimmerEffect(smallRow: Boolean = false, largeRow: Boolean = false) {
    if (smallRow) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onBackground)

        ) {
            repeat(1) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(30.dp)
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer(),
                            color = MaterialTheme.colorScheme.background.copy(alpha = 0.2f),
                            shape = MaterialTheme.shapes.medium
                        )
                )
            }
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            repeat(1) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(120.dp)
                        .padding(top = 8.dp)
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer(),
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                            shape = MaterialTheme.shapes.medium
                        )
                )
            }
        }
    }

}