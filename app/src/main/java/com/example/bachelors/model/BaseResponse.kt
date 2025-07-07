package com.example.bachelors.model

import com.google.gson.annotations.SerializedName


data class BaseResponse(
    @SerializedName("Name") var Name: String? = null,
    @SerializedName("Total_Paid") var TotalPaid: Int? = null,
    @SerializedName("Total_Expense_by_Self") var totalExpenseBySelf: Double? = null,
    @SerializedName("Will_Get") var WillGet: Double? = null,
    @SerializedName("Meal_Per_Person") var MealPerPerson: Int? = null,
    @SerializedName("Total_Meal_in_Mess") var TotalMealInMess: Int? = null,
    @SerializedName("Total_Expenses") var TotalExpenses: Int? = null,
    @SerializedName("Total_Per_Meal_Cost") var TotalPerMealCost: Double? = null,
    @SerializedName("Current_Date") var CurrentDate: String? = null
)