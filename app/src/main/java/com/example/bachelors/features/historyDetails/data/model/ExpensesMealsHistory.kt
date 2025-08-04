package com.example.bachelors.features.historyDetails.data.model

import com.google.gson.annotations.SerializedName

data class ExpensesMealsHistory(
    @SerializedName("expenses_histories") val expensesHistories: List<ExpensesMealsHistories>? = null,
    @SerializedName("meal_histories") val mealsHistories: List<ExpensesMealsHistories>? = null
)