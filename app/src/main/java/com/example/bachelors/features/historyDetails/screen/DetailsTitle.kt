package com.example.bachelors.features.historyDetails.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun DetailsTitle() {
    Row(modifier = Modifier.padding(10.dp).background(MaterialTheme.colorScheme.background)) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
                .padding(start = 6.dp),
            text = "Date",
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f),
            text = "Day",
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
                .padding(end = 6.dp),
            text = "Meals",
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.End
        )
    }
}