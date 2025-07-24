package com.example.bachelors.core.common.network

import com.example.bachelors.core.common.data.model.HistoryItemResponse
import com.example.bachelors.core.common.data.model.MonthListResponse
import com.example.bachelors.core.common.data.model.UserInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeHistoryService {

    @GET("echo")
    suspend fun getHomeHistory(
        @Query("name") name: String,
        @Query("month") month: String,
        @Query("action") action: String,
    ): HistoryItemResponse

    @GET("echo")
    suspend fun getUserCurrentMealInfo(
        @Query("name") name: String,
        @Query("action") action: String
    ): UserInfoResponse

    @GET("echo")
    suspend fun getMonthList(
        @Query("action") action: String
    ): MonthListResponse
}