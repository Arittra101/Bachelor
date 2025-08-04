package com.example.bachelors.features.historyDetails.data

import com.example.bachelors.features.historyDetails.data.model.ExpensesMealsHistories
import com.example.bachelors.features.historyDetails.domain.HistoryDetailsRepo
import com.example.bachelors.features.historyDetails.network.HistoryDetailsService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HistoryDetailsRepoImpl(
    private val historyDetailsService: HistoryDetailsService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : HistoryDetailsRepo {
    override suspend fun getMealHistory(
        name: String,
        month: String,
        action: String
    ): Flow<List<ExpensesMealsHistories>> {
        return flow {
            val result = historyDetailsService.getMealHistory(name, month, action).data?.get(0)?.mealsHistories ?: listOf()
            emit(result)
        }.flowOn(dispatcher)
    }

    override suspend fun getExpensesHistory(
        name: String,
        month: String,
        action: String
    ): Flow<List<ExpensesMealsHistories>> {
        return flow {
            val result = historyDetailsService.getExpenseHistory(name, month, action).data?.get(0)?.expensesHistories ?: listOf()
            emit(result)
        }.flowOn(dispatcher)
    }
}