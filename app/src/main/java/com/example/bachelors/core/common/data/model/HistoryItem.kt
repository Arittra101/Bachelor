package com.example.bachelors.core.common.data.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryItem(
    @SerializedName("month_name") val monthName: String,
    @SerializedName("total_mess_expensses") val totalMessExpenses: Double,
    @SerializedName("total_member_expensses") val totalMemberExpenses: Double,
    @SerializedName("total_mess_meal") val totalMessMeal: Double,
    @SerializedName("other_avg_cost") val otherAvgCost: Double
): Parcelable