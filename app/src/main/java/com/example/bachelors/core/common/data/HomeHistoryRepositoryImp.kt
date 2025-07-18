package com.example.bachelors.core.common.data

import com.example.bachelors.core.common.data.model.HistoryItem
import com.example.bachelors.core.common.data.model.UserInfo
import com.example.bachelors.core.common.domain.repository.HomeHistoryRepository
import com.example.bachelors.core.common.network.HomeHistoryService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomeHistoryRepositoryImp(
    private val homeHistoryService: HomeHistoryService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    HomeHistoryRepository {
    override suspend fun getHistory(
        name: String,
        month: String,
        action: String
    ): Flow<List<HistoryItem>> {
        return flow {
            val result = homeHistoryService.getHomeHistory().data ?: listOf()
            emit(result)
        }.flowOn(dispatcher)
    }

    override suspend fun getUserCurrentMealInfo(
        name: String,
        action: String
    ): Flow<List<UserInfo>> {
        return flow {
            val result = homeHistoryService.getUserCurrentMealInfo(name,action).data ?: listOf()
            emit(result)
        }.flowOn(dispatcher)
    }

    override suspend fun getMonthList(action: String): Flow<List<String>>{
        return flow{
           val result = homeHistoryService.getMonthList(action).data[0]
            emit(result)
        }.flowOn(dispatcher)
    }

}