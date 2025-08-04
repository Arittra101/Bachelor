package com.example.bachelors.features.historyDetails.data.model

import com.example.bachelors.core.common.data.model.HistoryItem
import com.google.gson.annotations.SerializedName

data class HistoryDetailsResponse(
    @SerializedName("data") var data: List<ExpensesMealsHistory>? = null
)

