package com.example.bachelors.features.historyDetails.network

import com.example.bachelors.features.historyDetails.data.model.HistoryDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HistoryDetailsService {
    @GET("echo")
    suspend fun getMealHistory(
        @Query("name") name: String,
        @Query("month") month: String,
        @Query("action") action: String
    ): HistoryDetailsResponse

    @GET("echo")
    suspend fun getExpenseHistory(
        @Query("name") name: String,
        @Query("month") month: String,
        @Query("action") action: String
    ): HistoryDetailsResponse
}