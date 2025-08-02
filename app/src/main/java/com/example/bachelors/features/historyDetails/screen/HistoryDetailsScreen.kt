package com.example.bachelors.features.historyDetails.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bachelors.features.common.screen.BaseScreen
import com.example.bachelors.features.historyDetails.ui.HistoryDetailsEvent
import com.example.bachelors.features.historyDetails.ui.HistoryDetailsMealState
import com.example.bachelors.features.historyDetails.ui.HistoryDetailsViewmodel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun HistoryDetailsRoutes(
    navController: NavController,
    viewModel: HistoryDetailsViewmodel = koinViewModel()
) {
    val state by viewModel.mealExpensesHistories.collectAsState()
    val onEvent = viewModel::handleHistoryDetailsEvent
    HistoryScreen(state, onEvent)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun HistoryScreen(state: HistoryDetailsMealState?, onEvent: (event: HistoryDetailsEvent) -> Unit) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { HistoryTabs.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    BaseScreen(title = "History", isXMlComponent = true, zeroBottomPadding = true) {
        Column() {
            TabRow(
                selectedTabIndex = selectedTabIndex.value,
                containerColor = MaterialTheme.colorScheme.primary
//                modifier = Modifier.fillMaxSize()
            ) {
                HistoryTabs.entries.forEachIndexed() { index, currentTap ->
                    Tab(
                        selected = index == selectedTabIndex.value,
                        selectedContentColor = MaterialTheme.colorScheme.onSecondary,
                        unselectedContentColor = MaterialTheme.colorScheme.secondaryContainer,
                        modifier = Modifier.padding(10.dp),
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(currentTap.ordinal)
                            }
                        },
                        content = {
                            Row {
                                Icon(
                                    imageVector = if (index == selectedTabIndex.value) currentTap.selectedIcon else currentTap.unselectedIcon,
                                    contentDescription = currentTap.title,
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = currentTap.title)
                            }
                        },
                    )
                }
            }
            DetailsTitle()
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()

            ) { page ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MealsExpensesScreen(state?.mealExpensesHistories,state?.isLoading)
//                    MonthHistoryScreenPreview(0.dp,initialHistoryItem,null)

                }
            }
        }
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .distinctUntilChanged()
            .collectLatest { page ->
                onEvent.invoke(HistoryDetailsEvent.Trigger(page))
            }
    }
}

enum class HistoryTabs(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val title: String

) {
    Meals(
        unselectedIcon = Icons.Outlined.Home,
        title = "Meals",
        selectedIcon = Icons.Filled.Home
    ),
    Expenses(
        unselectedIcon = Icons.Outlined.Home,
        title = "Expenses",
        selectedIcon = Icons.Filled.Home
    )
}