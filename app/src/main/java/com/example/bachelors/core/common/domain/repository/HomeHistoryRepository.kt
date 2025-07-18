package com.example.bachelors.core.common.domain.repository

import com.example.bachelors.core.common.data.model.HistoryItem
import com.example.bachelors.core.common.data.model.UserInfo
import kotlinx.coroutines.flow.Flow
import java.time.Month

interface HomeHistoryRepository {
    suspend fun getUserCurrentMealInfo(name: String, action: String): Flow<List<UserInfo>>
    suspend fun getHistory(name: String,month: String, action: String): Flow<List<HistoryItem>>
    suspend fun getMonthList(action: String): Flow<List<String>>
}