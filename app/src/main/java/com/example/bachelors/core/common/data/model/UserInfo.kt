package com.example.bachelors.core.common.data.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("Name") var Name: String? = null,
    @SerializedName("Total_Paid") var TotalPaid: String? = null,
    @SerializedName("Total_Expense_by_Self") var totalExpenseBySelf: String? = null,
    @SerializedName("Will_Get") var WillGet: String? = null,
    @SerializedName("Meal_Per_Person") var MealPerPerson: String? = null,
    @SerializedName("Total_Meal_in_Mess") var TotalMealInMess: String? = null,
    @SerializedName("Total_Expenses") var TotalExpenses: String? = null,
    @SerializedName("Total_Per_Meal_Cost") var TotalPerMealCost: String? = null,
    @SerializedName("Current_Date") var CurrentDate: String? = null,
    @SerializedName("others_cost") var OthersCost: String? = null,
)

