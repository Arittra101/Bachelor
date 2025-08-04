package com.example.bachelors.features.historyDetails.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExpensesMealsHistories(
    @SerializedName("date") val date: String? = null,
    @SerializedName("day") val day: String? = null,
    @SerializedName("amount") val amount: Float? = null,
    @SerializedName("total_meal") val totalMeal: Int? = null,
): Parcelable
