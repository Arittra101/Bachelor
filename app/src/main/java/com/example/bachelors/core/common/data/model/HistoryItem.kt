package com.example.bachelors.core.common.data.model
import com.google.gson.annotations.SerializedName

data class HistoryItem(
    @SerializedName("name") val name: String,
    @SerializedName("total_mess_expensses") val totalMessExpenses: Double,
    @SerializedName("total_member_expensses") val totalMemberExpenses: Double,
    @SerializedName("total_mess_meal") val totalMessMeal: Double,
    @SerializedName("other_avg_cost") val otherAvgCost: Double
)