package com.example.bachelors.features.historyDetails.screen


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bachelors.features.common.screen.CustomShimmerEffect
import com.example.bachelors.features.historyDetails.data.model.ExpensesMealsHistories
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import kotlinx.coroutines.delay


@OptIn(ExperimentalFoundationApi::class)
@Composable
//@Preview
fun MealsExpensesScreen(mealExpensesHistories: List<ExpensesMealsHistories>?, loading: Boolean?) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        if (loading == true) {
            items(3) {
                CustomShimmerEffect(smallRow = true)
            }
        } else {
            val demoMonthHistoryList = mealExpensesHistories ?: emptyList()
            itemsIndexed(
                demoMonthHistoryList,
                key = { _, item -> item.hashCode() }) { index, item ->
                var visible by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) {
                    delay(index * 100L) // delay for staggered effect
                    visible = true
                }
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    MealExpensesItemScreen(item, modifier = Modifier.animateItemPlacement())
                }
            }
        }
    }
}


@Composable
fun MealExpensesItemScreen(
    expensesMealsHistories: ExpensesMealsHistories,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onBackground)
            .padding(4.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
                .padding(start = 6.dp),
            text = expensesMealsHistories.date ?: "",
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Start
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f),
            text = expensesMealsHistories.day ?: "",
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
                .padding(end = 6.dp),
            text = if (expensesMealsHistories.amount != null) expensesMealsHistories.amount.toString() else expensesMealsHistories.totalMeal.toString(),
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.End
        )
    }
}