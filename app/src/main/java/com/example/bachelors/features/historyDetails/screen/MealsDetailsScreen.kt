package com.example.bachelors.features.historyDetails.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bachelors.features.historyDetails.data.model.ExpensesMealsHistories

//@Composable

//fun MealsExpensesScreenRoutes(navController: NavController?) {
//
//
//    MealsExpensesScreen(state.mealExpensesHistories)
//}

@Composable
//@Preview
fun MealsExpensesScreen(mealExpensesHistories: List<ExpensesMealsHistories>?) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary),

        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val demoMonthHistoryList = mealExpensesHistories ?: emptyList()

//        val demoMonthHistoryList = listOf(
//            MonthHistory(1, "August", 4965.0, 906.25, 20, 250.0),
//            MonthHistory(2, "April", 4965.0, 796.875, 24, 250.0),
//            MonthHistory(3, "March", 4965.0, 906.25, 20, 250.0),
//            MonthHistory(4, "May", 5165.0, 956.25, 20, 250.0),
//            MonthHistory(5, "June", 5000.0, 900.0, 22, 260.0)
//        )
        items(demoMonthHistoryList, key = { it}) {
            MealExpensesItemScreen(it)
//            Spacer(modifier = Modifier.weight())
        }
    }
}


@Composable
fun MealExpensesItemScreen(expensesMealsHistories: ExpensesMealsHistories) {
    Row(modifier = Modifier
        .background(MaterialTheme.colorScheme.onBackground)
        .padding(4.dp)) {
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
            text = if(expensesMealsHistories.amount != null) expensesMealsHistories.amount.toString() else expensesMealsHistories.totalMeal.toString(),
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.End
        )
    }
}