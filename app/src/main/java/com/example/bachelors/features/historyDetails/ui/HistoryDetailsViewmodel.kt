package com.example.bachelors.features.historyDetails.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bachelors.features.historyDetails.data.model.ExpensesMealsHistories
import com.example.bachelors.features.historyDetails.domain.HistoryDetailsRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HistoryDetailsViewmodel(private val historyDetailsRepo: HistoryDetailsRepo) : ViewModel() {

    private val _mealExpensesHistories = MutableStateFlow<HistoryDetailsMealState?>(null)
    val mealExpensesHistories = _mealExpensesHistories

    private var mealsHistories: HistoryDetailsMealState = HistoryDetailsMealState(mealExpensesHistories = listOf(), isLoading = true)
    private var expensesHistories: HistoryDetailsMealState = HistoryDetailsMealState(mealExpensesHistories = listOf(), isLoading = true)

    private var page: Int = -1
    init {
        fetchMealHistory()
        fetchExpensesHistory()
    }

    fun fetchMealHistory(name: String = "Dhrubo", month: String="april", action: String="getMemberMealHistory") {
        viewModelScope.launch {
            historyDetailsRepo.getMealHistory(name, month, action).collect {
                mealsHistories = HistoryDetailsMealState(mealExpensesHistories = it, isLoading = false)
                triggerState(page)
                Log.wtf("crey","meal ${mealsHistories}")

            }
        }
    }


    fun fetchExpensesHistory(name: String = "Dhrubo", month: String="april", action: String="getExpense") {
        viewModelScope.launch {
            historyDetailsRepo.getExpensesHistory(name, month, action).collect {
                expensesHistories = HistoryDetailsMealState(mealExpensesHistories = it, isLoading = false)
                triggerState(page)
                Log.wtf("crey","cost ${expensesHistories}")
            }
        }
    }

    private fun triggerState(page: Int) {
        this.page = page
        if (page==0) _mealExpensesHistories.value = mealsHistories
        else _mealExpensesHistories.value = expensesHistories
    }

    fun handleHistoryDetailsEvent(event: HistoryDetailsEvent) {
        when (event) {
            is HistoryDetailsEvent.Trigger -> {
                triggerState(event.page)
            }
        }
    }

}


data class HistoryDetailsMealState(
    val mealExpensesHistories: List<ExpensesMealsHistories>? = null,
    val isLoading: Boolean = true,
)

sealed class HistoryDetailsMealUiEffect() {
    data class ShowError(val message: String) : HistoryDetailsMealUiEffect()
    data class ShowSuccess(val message: String) : HistoryDetailsMealUiEffect()
}

sealed class HistoryDetailsEvent() {
    data class Trigger(var page: Int) : HistoryDetailsEvent()
}
//Meal Expenses
//data class HistoryDetailsExpensesState(
//    val expensesHistories: List<ExpensesMealsHistories>? = null,
//    val isLoading: Boolean = true
//)
//sealed class HistoryDetailsExpensesUiEffect(){
//    data class ShowError(val message: String) : HistoryDetailsMealUiEffect()
//    data class ShowSuccess(val message: String) : HistoryDetailsMealUiEffect()
//}
//
//sealed class HistoryDetailsState(){
//    data object List<ExpensesMealsHistories>: HistoryDetailsState()
//    data object Loading : HistoryDetailsState()
//    data class Success(val data: List<Any> = emptyList()) : HistoryDetailsState()
//    data class Error(val message: String) : HistoryDetailsState()
//}