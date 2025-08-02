package com.example.bachelors.features.history.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bachelors.R
import com.example.bachelors.core.common.data.model.HistoryItem
import com.example.bachelors.features.common.HistoryScreenEvent
import com.example.bachelors.features.common.HistoryState
import com.example.bachelors.features.common.HomeHistoryViewModel
import com.example.bachelors.features.common.screen.BaseScreen
import org.koin.androidx.compose.koinViewModel


@Composable
internal fun MonthHistoryScreenRoute(
    navController: NavController,
    viewModel: HomeHistoryViewModel = koinViewModel()

) {
    val state = viewModel.historyState
    val onEvent = viewModel::handleHistoryEvent
    MonthHistoryScreen(state,onEvent,navController)
}

@Composable
fun MonthHistoryScreen(state: HistoryState, onEvent: (event: HistoryScreenEvent) -> Unit,navController:NavController) {

    BaseScreen(title = "History", isXMlComponent = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),

        ) {
            Column(
                modifier = Modifier
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 0.dp)
                ) {
                    val demoMonthHistoryList = state.historyItem ?: emptyList()
                        /*listOf(
                        MonthHistory(1, "August", 4965.0, 906.25, 20, 250.0),
                        MonthHistory(2, "April", 4965.0, 796.875, 24, 250.0),
                        MonthHistory(3, "March", 4965.0, 906.25, 20, 250.0),
//                        MonthHistory(4, "May", 5165.0, 956.25, 20, 250.0),
//                        MonthHistory(5, "June", 5000.0, 900.0, 22, 260.0)
                    )*/
                    items(demoMonthHistoryList, key = { it }) {
                        MonthHistoryScreenPreview(10.dp,it,navController)
                    }
                }
            }
        }
    }
}

@Composable
fun MonthHistoryScreenPreview(paddingValues: Dp,data: HistoryItem,navController: NavController?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = paddingValues)
            .clip(shape = RoundedCornerShape(corner = CornerSize(8.dp)))
            .background(color = MaterialTheme.colorScheme.onBackground)
            .clickable { navController?.navigate(R.id.historyDetailsFragment) }

    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp),
                    painter = painterResource(id = R.drawable.date),
                    contentDescription = "Favorite Icon",
                    tint = MaterialTheme.colorScheme.secondary
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = "January 2023",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    modifier = Modifier
                        .width(10.dp)
                        .height(10.dp),
                    painter = painterResource(id = R.drawable.details),
                    contentDescription = "Favorite Icon",
                    tint = MaterialTheme.colorScheme.secondary
                )

            }
            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = "Financial Summary",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 12.sp,
            )

            Row(
                modifier = Modifier.padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MonthHistoryCostSummary(R.drawable.money)
                Spacer(modifier = Modifier.weight(1f))
                MonthHistoryCostSummary(R.drawable.money)
                Spacer(modifier = Modifier.weight(1f))
                MonthHistoryCostSummary(R.drawable.money)
            }
        }
    }
}

@Composable
fun MonthHistoryCostSummary(icon: Int) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp),
            painter = painterResource(id = icon),
            contentDescription = "Favorite Icon",
            tint = MaterialTheme.colorScheme.secondary,
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = "Total Mess Cost",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = "$400.00",
            color = Color.Red,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

data class MonthHistory(
    val id: Int,
    val monthName: String,
    val totalMessExpenses: Double,
    val totalMemberExpenses: Double,
    val totalMessMeal: Int,
    val otherAvgCost: Double
)