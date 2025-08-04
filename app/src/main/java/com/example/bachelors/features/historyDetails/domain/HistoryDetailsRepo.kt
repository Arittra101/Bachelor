package com.example.bachelors.features.historyDetails.domain
import com.example.bachelors.features.historyDetails.data.model.ExpensesMealsHistories
import kotlinx.coroutines.flow.Flow

interface HistoryDetailsRepo  {
    suspend fun getMealHistory(name: String, month: String, action: String): Flow<List<ExpensesMealsHistories>>
    suspend fun getExpensesHistory(name: String, month: String, action: String): Flow<List<ExpensesMealsHistories>>
}

