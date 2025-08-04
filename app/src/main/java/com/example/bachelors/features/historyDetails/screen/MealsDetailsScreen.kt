package com.example.bachelors.features.historyDetails.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bachelors.features.common.screen.Animation
import com.example.bachelors.features.common.screen.CustomShimmerEffect
import com.example.bachelors.features.historyDetails.data.model.ExpensesMealsHistories

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

                Animation(item, index) { expensesMealsHistories ->
                    MealExpensesItemScreen(
                        expensesMealsHistories as ExpensesMealsHistories,
                        modifier = Modifier.animateItemPlacement()
                    )
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